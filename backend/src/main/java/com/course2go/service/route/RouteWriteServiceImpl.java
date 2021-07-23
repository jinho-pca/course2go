package com.course2go.service.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.BoardDao;
import com.course2go.dao.RouteDao;
import com.course2go.model.board.Board;
import com.course2go.model.route.Route;

@Service
public class RouteWriteServiceImpl implements RouteWriteService {
	
	@Autowired
	RouteDao routeDao;
	@Autowired
	BoardDao boardDao;
	
	@Override
	public void writeRoute(String uid, Route route) {
		Board board = new Board();
		
		boardDao.save(board);
		routeDao.save(route);
	}

}
