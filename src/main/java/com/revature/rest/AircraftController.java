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
import com.revature.db.Craft;

@Path("/aircraft")
public class AircraftController {

	static ObjectMapper objectMapper;
	static {
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getCrafts(@DefaultValue("1") @QueryParam("page") int page,
			@DefaultValue("") @QueryParam("search") String searchparam) {
		List<Craft> c = Access.getAccess().Craft.getCrafts(page, searchparam);
		int craftCount = Access.getAccess().Craft.getCount();
		StringBuilder sb = new StringBuilder();
		sb.append("{\"count\":");
		sb.append(craftCount);
		sb.append(",\"next\":");
		if (craftCount > (page) * 10) {
			sb.append("\"" + Constants.url + "aircraft?page=");
			sb.append(page + 1);
			sb.append("\"");
		} else
			sb.append("null");
		sb.append(",\"previous\":");
		if (page > 1) {
			sb.append("\"" + Constants.url + "aircraft?page=");
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
		return sb.toString().replaceAll(",\"pilots\":\\[\\]", "");
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCraft(@PathParam("id") int id) {
		Craft c = Access.getAccess().Craft.getByID(id);
		try {
			return objectMapper.writeValueAsString(c).replaceAll(",\"craft\":\\[\\]", "");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{}";
		}
	}

	@GET
	@Path("/schema/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCraftSchema() {
		return "{ \"title\": \"Aircraft Object\", \"$schema\": \"http://json-schema.org/draft-04/schema\", \"description\": \"An aircraft that Flight School pilots are trained and certified to fly.\", \"type\": \"object\", \"required\": [  \"id\",  \"name\",  \"certs\",  \"pilot_count\",  \"url\" ], \"properties\": {  \"id\": {   \"description\": \"The unique integer identifier for this aircraft.\",   \"type\": \"integer\"  },  \"name\": {   \"description\": \"The name of the aircraft.\",   \"type\": \"string\"  },  \"certs\": {   \"description\": \"A comma-separated list of the certifications that allow a pilot to fly this aircraft.\",   \"type\": \"string\"  },  \"pilot_count\": {   \"description\": \"The number of pilots at Flight School who are certified to fly this aircraft.\",   \"type\": \"integer\"  },  \"url\": {   \"format\": \"uri\",   \"description\": \"The hypermedia URL of this resource.\",   \"type\": \"string\"  },  \"pilots\": {   \"description\": \"An array of the pilots at Flight School who are certified to fly this aircraft.\",   \"type\": \"array\",   \"items\": {    \"type\": \"object\",    \"required\": [     \"id\",     \"name\",     \"dob\",     \"sex\",     \"url\",     \"age\",     \"aircraft_count\"    ],    \"properties\": {     \"id\": {      \"description\": \"The unique integer identifier for this pilot.\",      \"type\": \"integer\"     },     \"name\": {	  \"description\": \"The pilot's name.\",      \"type\": \"string\"     },     \"dob\": {	  \"format\": \"date\",	  \"description\": \"The pilot's date of birth in YYYY-MM-DD format.\",      \"type\": \"string\"     },     \"sex\": {	  \"description\": \"The pilot's sex.\",      \"type\": \"string\"     },     \"url\": {	  \"description\": \"The hypermedia URL for the pilot.\",	  \"format\": \"uri\",      \"type\": \"string\"     },     \"age\": {	  \"description\": \"The pilot's age in years.\",      \"type\": \"integer\"     },     \"aircraft_count\": {	  \"description\": \"The number of aircraft this pilot is certified to fly.\",      \"type\": \"integer\"     }    }   }  } }}";
	}

}
