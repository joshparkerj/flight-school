package com.revature.rest;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Constants;
import com.revature.db.Access;
import com.revature.db.Cert;

@Path("/certification")
public class CertificationController {

	static ObjectMapper objectMapper;
	static {
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getCerts(@DefaultValue("1") @QueryParam("page") int page,
			@DefaultValue("") @QueryParam("search") String searchparam) {
		List<Cert> c = Access.getAccess().Cert.getCerts(page, searchparam);
		int certCount = Access.getAccess().Cert.getCount();
		StringBuilder sb = new StringBuilder();
		sb.append("{\"count\":");
		sb.append(certCount);
		sb.append(",\"next\":");
		if (certCount > (page) * 10) {
			sb.append("\"" + Constants.url + "certification?page=");
			sb.append(page + 1);
			sb.append("\"");
		} else
			sb.append("null");
		sb.append(",\"previous\":");
		if (page > 1) {
			sb.append("\"" + Constants.url + "certification?page=");
			sb.append(page - 1);
			sb.append("\"");
		} else
			sb.append("null");
		sb.append(",\"results\":");
		try {
			sb.append(objectMapper.writeValueAsString(c));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			sb.append("null");
		}
		sb.append("}");
		return sb.toString().replaceAll(",\"pilots\":\\[\\],\"craft\":\\[\\]", "");
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCraft(@PathParam("id") int id) {
		Cert c = Access.getAccess().Cert.getByID(id);
		try {
			return objectMapper.writeValueAsString(c).replaceAll("(,\"pilots\":\\[\\]|,\"craft\":\\[\\])", "");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{}";
		}
	}

	@GET
	@Path("/schema/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCertSchema() {
		return "{ \"title\": \"Certification Object\", \"$schema\": \"http://json-schema.org/draft-04/schema\", \"description\": \"A certification that we offer here at Flight School.\", \"type\": \"object\", \"required\": [  \"id\",  \"name\",  \"craft_count\",  \"pilot_count\",  \"url\" ], \"properties\": {  \"id\": {   \"description\": \"The unique integer identifier for this certification.\",   \"type\": \"integer\"  },  \"name\": {   \"description\": \"The name of this certification.\",   \"type\": \"string\"  },  \"craft_count\": {   \"description\": \"The number of aircraft that a pilot who earns this certification will be certified to fly.\",   \"type\": \"integer\"  },  \"pilot_count\": {   \"description\": \"The number of pilots who have earned this certification here at Flight School.\",   \"type\": \"integer\"  },  \"url\": {   \"format\": \"uri\",   \"description\": \"The hypermedia URL of this resource.\",   \"type\": \"string\"  },  \"pilots\": {   \"description\": \"An array of the pilots who have earned this certification here at Flight School.\",   \"type\": \"array\",   \"items\": {    \"type\": \"object\",    \"required\": [     \"id\",     \"name\",     \"dob\",     \"sex\",     \"url\",     \"age\",     \"aircraft_count\"    ],    \"properties\": {     \"id\": {	  \"description\": \"The unique integer identifier for this pilot.\",      \"type\": \"integer\"     },     \"name\": {	  \"description\": \"The pilot's name.\",      \"type\": \"string\"     },     \"dob\": {	  \"format\": \"date\",	  \"description\": \"The pilot's date of birth in YYYY-MM-DD format.\",      \"type\": \"string\"     },     \"sex\": {	  \"description\": \"The pilot's sex.\",      \"type\": \"string\"     },     \"url\": {	  \"format\": \"uri\",	  \"description\": \"The hypermedia URL for the pilot.\",      \"type\": \"string\"     },     \"age\": {	  \"description\": \"The pilot's age in years.\",      \"type\": \"integer\"     },     \"aircraft_count\": {	  \"description\": \"The number of aircraft this pilot has been trained and certified to fly.\",      \"type\": \"integer\"     }    }   }  },  \"craft\": {   \"description\": \"An array of the aircraft that a pilot who earns this certification will be certified to fly.\",   \"type\": \"array\",   \"items\": {    \"type\": \"object\",    \"required\": [     \"id\",     \"name\",     \"certs\",     \"pilot_count\",     \"url\"    ],    \"properties\": {     \"id\": {	  \"description\": \"The unique integer identifier for the aircraft.\",      \"type\": \"integer\"     },     \"name\": {	  \"description\": \"The name of the aircraft.\",      \"type\": \"string\"     },     \"certs\": {	  \"description\": \"A comma-separated list of the certifications that allow a pilot to fly this aircraft.\",      \"type\": \"string\"     },     \"pilot_count\": {	  \"description\": \"The number of pilots at Flight School who are certified to fly this aircraft.\",      \"type\": \"integer\"     },     \"url\": {	  \"description\": \"The hypermedia URL for the aircraft.\",      \"type\": \"string\"     }    }   }  } }}";
	}

}
