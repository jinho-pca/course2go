package com.course2go.service.route;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.RouteDao;
import com.course2go.model.route.Route;
import com.course2go.model.route.RouteProfileResponse;
import com.course2go.model.route.RouteReadResponse;
import com.course2go.model.route.RouteResponse;
import com.course2go.model.route.RouteWriteRequest;
import com.course2go.service.board.BoardService;
import com.course2go.service.contain.ContainService;

@Service
public class RouteServiceImpl implements RouteService {
	
	@Autowired
	RouteDao routeDao;
	@Autowired
	BoardService boardService;
	@Autowired
	ContainService containService;
	
	@Override
	public void writeRoute(String uid, RouteWriteRequest request) {
		writeRoute(uid, request.getTitle(), request.getRouteStartDate(), request.getRouteEndDate(), request.getRouteContent(), request.getRoutePid());
	}

	@Override
	public void writeRoute(String uid, String title, LocalDate routeStartDate, LocalDate routeEndDate, String routeContent,
			List<Integer> routePid) {
		int boardRid = routeDao.save(Route.builder(routeStartDate, routeEndDate, routeContent).build()).getRid();
		boardService.writeBoard(uid, title, 0, 0, boardRid, true);
		int order = 1;
		for (Integer pid : routePid) {
			containService.writeContain(boardRid, pid, null, order++);
		}
	}

	@Override
	public RouteReadResponse readRouteBoard(Integer bid) {
		RouteReadResponse routeReadResponse= new RouteReadResponse();
		routeReadResponse.setBoardResponse(boardService.readBoard(bid));
		routeReadResponse.setRouteResponse(readRoute(routeReadResponse.getBoardResponse().getBoardTid()));
		routeReadResponse.setContainSpots(containService.listContain(routeReadResponse.getBoardResponse().getBoardTid()));
		return routeReadResponse;
	}

	@Override
	public RouteResponse readRoute(Integer rid) {
		RouteResponse routeResponse = new RouteResponse();
		Route route = routeDao.getById(rid);
		routeResponse.setRouteContent(route.getRouteContent());
		routeResponse.setRouteStartDate(route.getRouteStartDate());
		routeResponse.setRouteEndDate(route.getRouteEndDate());
		return routeResponse;
	}

	@Override
	public List<RouteReadResponse> getRouteList(String uid) {
		return null;
	}

}
