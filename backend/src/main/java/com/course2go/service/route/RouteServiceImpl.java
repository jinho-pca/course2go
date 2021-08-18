package com.course2go.service.route;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.RouteDao;
import com.course2go.model.ExtraData;
import com.course2go.model.board.BoardDto;
import com.course2go.model.board.BoardResponse;
import com.course2go.model.route.Route;
import com.course2go.model.route.RouteReadResponse;
import com.course2go.model.route.RouteResponse;
import com.course2go.model.route.RouteWriteRequest;
import com.course2go.model.user.UserDto;
import com.course2go.service.board.BoardService;
import com.course2go.service.boardlike.BoardlikeService;
import com.course2go.service.contain.ContainService;
import com.course2go.service.user.UserService;

@Service
public class RouteServiceImpl implements RouteService {
	
	@Autowired
	RouteDao routeDao;
	@Autowired
	BoardService boardService;
	@Autowired
	ContainService containService;
	@Autowired
	BoardlikeService boardlikeService;
	@Autowired
	UserService userService;
	
	@Override
	public void writeRoute(String uid, RouteWriteRequest request, String imageUrl) {
		writeRoute(uid, request.getTitle(), request.getRouteStartDate(), request.getRouteEndDate(), request.getRouteContent(), request.getRoutePid(), imageUrl);
	}

	@Override
	public void writeRoute(String uid, String title, LocalDate routeStartDate, LocalDate routeEndDate, String routeContent,
			List<Integer> routePid, String imageUrl) {
		int boardRid = routeDao.save(Route.builder(routeStartDate, routeEndDate, routeContent, imageUrl).build()).getRid();
		boardService.writeBoard(uid, title, 0, 0, boardRid, true);
		int order = 1;
		for (Integer pid : routePid) {
			containService.writeContain(boardRid, pid, null, order++);
		}
	}

	@Override
	public RouteReadResponse readRouteBoard(Integer bid, String uid) {
		RouteReadResponse routeReadResponse= new RouteReadResponse();
		routeReadResponse.setBoardResponse(boardService.readBoard(bid, uid));
		routeReadResponse.setRouteResponse(readRoute(routeReadResponse.getBoardResponse().getBoardTid()));
		routeReadResponse.setContainSpots(containService.listContain(routeReadResponse.getBoardResponse().getBoardTid()));
		routeReadResponse.setUserDto(userService.getUserDtoByUid(routeReadResponse.getBoardResponse().getBoardWriterUid()));
		return routeReadResponse;
	}

	@Override
	public RouteResponse readRoute(Integer rid) {
		RouteResponse routeResponse = new RouteResponse();
		Route route = routeDao.findRouteByRid(rid);
		routeResponse.setRouteContent(route.getRouteContent());
		routeResponse.setRouteStartDate(route.getRouteStartDate());
		routeResponse.setRouteEndDate(route.getRouteEndDate());
		routeResponse.setRouteImage(route.getRouteImage());
		return routeResponse;
	}

	@Override
	public List<RouteReadResponse> getMyRouteList(String uid1, String uid) {
		List<RouteReadResponse> routeList = new LinkedList<RouteReadResponse>();
		List<BoardDto> list = boardService.getListbyUid(uid1);
		for (BoardDto boardDto : list) {
			if(!boardDto.getBoardType()) {
				continue;
			}
			RouteReadResponse routeReadResponse= new RouteReadResponse();
			routeReadResponse.setBoardResponse(new BoardResponse(boardDto.getBid(),boardDto.getBoardWriterUid(), boardDto.getBoardTitle(), boardDto.getBoardLike(), boardDto.getBoardStar(), boardDto.getBoardTid(), boardDto.getBoardType(), boardDto.getBoardTime(), boardlikeService.iLike(uid, boardDto.getBid())));
			routeReadResponse.setRouteResponse(readRoute(boardDto.getBoardTid()));
			routeReadResponse.setContainSpots(containService.listContain(boardDto.getBoardTid()));
			routeReadResponse.setUserDto(userService.getUserDtoByUid(routeReadResponse.getBoardResponse().getBoardWriterUid()));
			routeList.add(routeReadResponse);
		}
		return routeList;
	}

	@Override
	public List<RouteReadResponse> getRouteList(String userNickname, String uid) {
		return getMyRouteList(userService.getUidByUserNickname(userNickname), uid);
	}

	@Override
	public List<RouteReadResponse> getRouteContainPids(List<Integer> pids, String uid) {
		List<RouteReadResponse> routeList = new LinkedList<RouteReadResponse>();
		List<Integer> rids = containService.getRidsContainPids(pids);
		for (Integer rid : rids) {
			RouteReadResponse routeReadResponse= new RouteReadResponse();
			routeReadResponse.setRouteResponse(readRoute(rid));
			routeReadResponse.setBoardResponse(boardService.getBoardRoute(rid, uid));
			routeReadResponse.setContainSpots(containService.listContain(rid));
			routeReadResponse.setUserDto(userService.getUserDtoByUid(routeReadResponse.getBoardResponse().getBoardWriterUid()));
			routeList.add(routeReadResponse);
		}
		return routeList;
	}

	@Transactional
	@Override
	public void deleteRoute(Integer rid) {
		containService.deleteContains(rid);
		routeDao.deleteById(rid);
	}

}
