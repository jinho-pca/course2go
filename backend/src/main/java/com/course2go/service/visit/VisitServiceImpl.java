package com.course2go.service.visit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.VisitDao;
import com.course2go.model.visit.Visit;
import com.course2go.model.visit.VisitReadResponse;
import com.course2go.model.visit.VisitResponse;
import com.course2go.model.visit.VisitWriteRequest;
import com.course2go.service.board.BoardService;
import com.course2go.service.place.PlaceService;

@Service
public class VisitServiceImpl implements VisitService {

	@Autowired
	VisitDao visitDao;
	@Autowired
	BoardService boardService;
	@Autowired
	PlaceService placeService;
	
	@Override
	public void writeVisit(String uid, VisitWriteRequest request) {
		writeVisit(uid, request.getVisitPid(), request.getVisitContent());
	}

	@Override
	public void writeVisit(String uid, Integer visitPid, String visitContent) {
		int boardvid = visitDao.save(Visit.builder(visitPid, visitContent).build()).getVid();
		boardService.writeBoard(uid, 0, 0, boardvid, false);
	}

	@Override
	public VisitReadResponse readVisitBoard(Integer bid) {
		VisitReadResponse visitReadResponse = new VisitReadResponse();
		visitReadResponse.setBoardResponse(boardService.readBoard(bid));
		visitReadResponse.setVisitResponse(readVisit(visitReadResponse.getBoardResponse().getBoardTid()));
		visitReadResponse.setPlace(placeService.getPlace(visitReadResponse.getVisitResponse().getVisitPid()));
		return visitReadResponse;
	}

	@Override
	public VisitResponse readVisit(Integer vid) {
		Visit visit = visitDao.getById(vid);
		return new VisitResponse(visit.getVid(), visit.getVisitContent());
	}

}
