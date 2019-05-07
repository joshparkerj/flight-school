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
import com.revature.db.Access;
import com.revature.db.Pilot;

@Path("/pilot")
public class PilotServlet {

	static ObjectMapper objectMapper;
	static {
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getPilots(@DefaultValue("0") @QueryParam("page") int page) {
		List<Pilot> p = Access.getAccess().Pilot.getPilots(page);
		int pilotCount = Access.getAccess().Pilot.getCount();
		StringBuilder sb = new StringBuilder();
		sb.append("{\"count\":");
		sb.append(pilotCount);
		sb.append(",\"next\":");
		if (pilotCount > (page)*10) {
			sb.append("\"https://flightschool.joshquizzes.com/api/pilot?page=");
			sb.append(page+1);
			sb.append("\"");
		} else sb.append("null");
		sb.append(",\"previous\":");
		if (page > 1) {
			sb.append("\"https://flightschool.joshquizzes.com/api/pilot?page=");
			sb.append(page-1);
			sb.append("\"");
		} else sb.append("null");
		sb.append(",\"results\":");
		try {
			sb.append(objectMapper.writeValueAsString(p));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			sb.append("null");
		}
		sb.append("}");
		return sb.toString();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPilot(@PathParam("id") int id) {
		Pilot p = Access.getAccess().Pilot.getByID(id);
		try {
			return objectMapper.writeValueAsString(p);
		} catch(JsonProcessingException e) {
			e.printStackTrace();
			return "{}";
		}
	}
	
}
