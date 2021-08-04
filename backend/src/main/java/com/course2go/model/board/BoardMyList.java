package com.course2go.model.board;

import java.util.LinkedList;
import java.util.List;

import com.course2go.model.place.PlaceDto;
import com.course2go.model.route.RouteReadResponse;
import com.course2go.model.route.RouteSpot;
import com.course2go.model.visit.VisitDto;
import com.course2go.model.visit.VisitReadResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class BoardMyList {
	List<RouteReadResponse> routeList;
	List<VisitReadResponse> visitList;
	public BoardMyList(){
		routeList = new LinkedList<RouteReadResponse>();
		visitList = new LinkedList<VisitReadResponse>();
	}
	
	public void addroute(RouteReadResponse route) {
		routeList.add(route);
	}
	
	public void addvisit(VisitReadResponse visit) {
		visitList.add(visit);
	}
}
