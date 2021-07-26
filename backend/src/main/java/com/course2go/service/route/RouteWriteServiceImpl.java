package com.course2go.service.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.BoardDao;
import com.course2go.dao.RouteDao;
import com.course2go.model.board.Board;
import com.course2go.model.route.Route;
import com.course2go.model.route.RouteWriteRequest;

@Service
public class RouteWriteServiceImpl implements RouteWriteService {
	
	@Autowired
	RouteDao routeDao;
	@Autowired
	BoardDao boardDao;
	
	@Override
	public void writeRoute(String uid, RouteWriteRequest request) {
		Board board = new Board();
		Route route = new Route();
		route.setRouteStartDate(request.getRouteStartDate());
		route.setRouteEndDate(request.getRouteEndDate());
		route.setRouteContent(request.getRouteContent());
		boardDao.save(board);
		routeDao.save(route);
		for (Integer pid : request.getRoutePid()) {
			
		}
	}

}
