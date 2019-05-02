package com.revature.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloController {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello World!";
	}
	
	@GET
	@Path("/{id}")
	@Produces("text/html")
	public String sayHelloLoop(@PathParam("id") int id) {
		String output = "";
		for (int i = 0; i < id; i++) {
			output += "Hi";
		}
		return output;
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHelloName(@FormParam("name") String name) {
		return "Hello, " + name;
	}
	
	@GET
	@Path("/artist")
	@Produces(MediaType.APPLICATION_JSON)
	public Artist getPrince() {
		return new Artist(1, "Prince");
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/artist")
	public Artist postArtist(Artist input) {
		return input;
	}
}
