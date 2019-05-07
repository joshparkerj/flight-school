package com.revature.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/v1")
public class HelloApplication extends Application{
	@Override
	public Set< Class<?> > getClasses() {
		final Set<Class<?>> returnValue = new HashSet<Class<?>>();
		returnValue.add(PilotServlet.class);
		returnValue.add(AircraftServlet.class);
		return returnValue;
	}
}
