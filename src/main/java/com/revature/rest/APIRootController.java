package com.revature.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revature.Constants;

@Path("/")
public class APIRootController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getRoot() {
		return "{\"pilots\":\"" + Constants.url + "pilot\",\"aircraft\":\"" + Constants.url
				+ "aircraft\",\"certifications\":\""+Constants.url+"certification\"}";
	}

}
