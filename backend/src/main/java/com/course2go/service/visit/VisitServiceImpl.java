package com.course2go.service.visit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.VisitDao;
import com.course2go.model.visit.Visit;
import com.course2go.model.visit.VisitDto;
import com.course2go.model.visit.VisitReadResponse;
import com.course2go.model.visit.VisitResponse;
import com.course2go.model.visit.VisitWriteDto;
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
	public void writeVisit(String uid, VisitWriteDto dto) {
		writeVisit(uid, dto.getVisitPid(), dto.getVisitContent(), dto.getVisitTime(), dto.getVisitCost(), dto.getVisitImage1(), dto.getVisitImage2(), dto.getVisitImage3());
	}

	@Override
	public void writeVisit(String uid, Integer visitPid, String visitContent, Integer visitTime, Integer visitCost, String visitImage1, String visitImage2, String visitImage3) {
		int boardvid = visitDao.save(Visit.builder(visitPid, visitContent, visitTime, visitCost, visitImage1, visitImage2, visitImage3).build()).getVid();
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
		return new VisitResponse(visit.getVisitPid(), visit.getVisitContent(), visit.getVisitTime(), visit.getVisitCost(), visit.getVisitImage1(), visit.getVisitImage2(), visit.getVisitImage3());
	}

	@Override
	public VisitDto getVisit(Integer vid) {
		if (vid==null) {
			return null;
		}
		Visit visit = visitDao.getById(vid);
		return new VisitDto(visit.getVid(), visit.getVisitPid(), visit.getVisitContent(), visit.getVisitTime(), visit.getVisitCost(), visit.getVisitImage1(), visit.getVisitImage2(), visit.getVisitImage3());
	}

}
