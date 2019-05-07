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
		try {
			return objectMapper.writeValueAsString(p);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{}";
		}
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
