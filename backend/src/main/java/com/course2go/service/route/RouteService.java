package com.course2go.service.route;

import java.time.LocalDate;
import java.util.List;

import com.course2go.model.route.RouteReadResponse;
import com.course2go.model.route.RouteResponse;
import com.course2go.model.route.RouteWriteRequest;

public interface RouteService {
	
	public void writeRoute(String uid, RouteWriteRequest request, String imageUrl);
	public void writeRoute(String uid, String title, LocalDate routeStartDate, LocalDate routeEndDate, String routeContent, List<Integer> routePid, String imageUrl);
	public RouteReadResponse readRouteBoard(Integer bid, String uid);
	public RouteResponse readRoute(Integer rid);
	public List<RouteReadResponse> getMyRouteList(String uid1, String uid);
	public List<RouteReadResponse> getRouteList(String userNickname, String uid);
	public List<RouteReadResponse> getRouteContainPids(List<Integer> pids, String uid);
	public void deleteRoute(Integer rid);
}
