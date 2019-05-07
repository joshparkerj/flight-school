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
import com.revature.db.Cert;

@Path("/certification")
public class CertificationServlet {

	static ObjectMapper objectMapper;
	static {
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getCrafts(@DefaultValue("0") @QueryParam("page") int page) {
		List<Cert> c = Access.getAccess().Cert.getCerts(page);
		int certCount = Access.getAccess().Cert.getCount();
		StringBuilder sb = new StringBuilder();
		sb.append("{\"count\":");
		sb.append(certCount);
		sb.append(",\"next\":");
		if (certCount > (page)*10) {
			sb.append("\"https://flightschool.joshquizzes.com/api/certification?page=");
			sb.append(page+1);
			sb.append("\"");
		} else sb.append("null");
		sb.append(",\"previous\":");
		if (page > 1) {
			sb.append("\"https://flightschool.joshquizzes.com/api/certification?page=");
			sb.append(page-1);
			sb.append("\"");
		} else sb.append("null");
		sb.append(",\"results\":");
		try {
			sb.append(objectMapper.writeValueAsString(c));
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
	public String getCraft(@PathParam("id") int id) {
		Cert c = Access.getAccess().Cert.getByID(id);
		try {
			return objectMapper.writeValueAsString(c);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{}";
		}
	}

}
