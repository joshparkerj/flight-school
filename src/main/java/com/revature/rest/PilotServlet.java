package com.revature.rest;

import java.text.SimpleDateFormat;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	/*
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getPilots() {
		return "Hello World!";
	}
	*/
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
	/*
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHelloName(@FormParam("name") String name) {
		return "Hello, " + name;
	}
	
	@GET
	@Path("/artist")
	@Produces(MediaType.APPLICATION_JSON)
	public PilotServlet getPrince() {
		return new PilotServlet(1, "Prince");
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/artist")
	public PilotServlet postArtist(PilotServlet input) {
		return input;
	}
	*/
	
}
