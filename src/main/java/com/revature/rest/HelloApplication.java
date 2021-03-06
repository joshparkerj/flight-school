package com.revature.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class HelloApplication extends Application{
	@Override
	public Set< Class<?> > getClasses() {
		final Set<Class<?>> returnValue = new HashSet<Class<?>>();
		returnValue.add(PilotController.class);
		returnValue.add(AircraftController.class);
		returnValue.add(CertificationController.class);
		returnValue.add(APIRootController.class);
		return returnValue;
	}
}
