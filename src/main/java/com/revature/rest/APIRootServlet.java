package com.revature.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class APIRootServlet {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getRoot() {
		return "{\"pilots\":\"https://flightschool.joshquizzes.com/api/pilot\",\"aircraft\":\"https://flightschool.joshquizzes.com/api/aircraft\",\"certifications\":\"https://flightschool.joshquizzes.com/api/certifications\"}";
	}

}
