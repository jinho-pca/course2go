package com.course2go.service.route;

import com.course2go.model.route.RouteWriteRequest;

public interface RouteWriteService {
	
	public void writeRoute(String uid, RouteWriteRequest request);
	
}
