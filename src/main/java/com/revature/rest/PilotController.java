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
import com.revature.db.Pilot;

@Path("/pilot")
public class PilotController {

	static ObjectMapper objectMapper;
	static {
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getPilots(@DefaultValue("1") @QueryParam("page") int page,
			@DefaultValue("") @QueryParam("search") String searchparam) {
		List<Pilot> p = Access.getAccess().Pilot.getPilots(page, searchparam);
		int pilotCount = Access.getAccess().Pilot.getCount();
		StringBuilder sb = new StringBuilder();
		sb.append("{\"count\":");
		sb.append(pilotCount);
		sb.append(",\"next\":");
		if (pilotCount > (page) * 10) {
			sb.append("\"" + Constants.url + "pilot?page=");
			sb.append(page + 1);
			sb.append("\"");
		} else
			sb.append("null");
		sb.append(",\"previous\":");
		if (page > 1) {
			sb.append("\"" + Constants.url + "pilot?page=");
			sb.append(page - 1);
			sb.append("\"");
		} else
			sb.append("null");
		sb.append(",\"results\":");
		try {
			sb.append(objectMapper.writeValueAsString(p));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			sb.append("null");
		}
		sb.append("}");
		return sb.toString().replaceAll(",\"craft\":\\[\\]", "");
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPilot(@PathParam("id") int id) {
		Pilot p = Access.getAccess().Pilot.getByID(id);
		try {
			return objectMapper.writeValueAsString(p).replaceAll(",\"pilots\":\\[\\]", "");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{}";
		}
	}

	@GET
	@Path("/schema/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPilotSchema() {
		return "{ \"title\": \"Pilot Object\", \"$schema\": \"http://json-schema.org/draft-04/schema\", \"description\": \"A pilot who has graduated from Flight School.\", \"type\": \"object\", \"required\": [  \"id\",  \"name\",  \"dob\",  \"sex\",  \"url\",  \"age\",  \"aircraft_count\" ], \"properties\": {  \"id\": {   \"description\": \"The unique integer identifier for this pilot.\",   \"type\": \"integer\"  },  \"name\": {   \"description\": \"The name of this pilot.\",   \"type\": \"string\"  },  \"dob\": {   \"format\": \"date\",   \"description\": \"The pilot's date of birth in YYYY-MM-DD format.\",   \"type\": \"string\"  },  \"sex\": {   \"description\": \"The pilot's sex.\",   \"type\": \"string\"  },  \"url\": {   \"description\": \"The hypermedia URL of this resource.\",   \"type\": \"string\"  },  \"age\": {   \"description\": \"The pilot's age in years.\",   \"type\": \"integer\"  },  \"aircraft_count\": {   \"description\": \"The number of aircraft this pilot has been trained and certified to fly.\",   \"type\": \"integer\"  },  \"craft\": {   \"description\": \"An array of the aircraft this pilot has been trained and certified to fly.\",   \"type\": \"array\",   \"items\": {    \"type\": \"object\",    \"required\": [     \"id\",     \"name\",     \"certs\",     \"pilot_count\",     \"url\"    ],    \"properties\": {     \"id\": {      \"description\": \"The unique integer that identifies the aircraft.\",      \"type\": \"integer\"     },     \"name\": {      \"description\": \"The name of the aircraft.\",      \"type\": \"string\"     },     \"certs\": {      \"description\": \"A comma-separated list of the certifications that allow a pilot to fly this aircraft.\",      \"type\": \"string\"     },     \"pilot_count\": {      \"description\": \"The number of pilots from Flight School who are certified to fly this aircraft.\",      \"type\": \"integer\"     },     \"url\": {      \"description\": \"The hypermedia URL for the aircraft.\",      \"type\": \"string\"     }    }   }  } }}";
	}

}
