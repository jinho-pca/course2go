package com.course2go.service.visit;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.VisitDao;
import com.course2go.model.ExtraData;
import com.course2go.model.board.BoardDto;
import com.course2go.model.board.BoardResponse;
import com.course2go.model.visit.Visit;
import com.course2go.model.visit.VisitDto;
import com.course2go.model.visit.VisitReadResponse;
import com.course2go.model.visit.VisitResponse;
import com.course2go.model.visit.VisitWriteDto;
import com.course2go.service.board.BoardService;
import com.course2go.service.contain.ContainService;
import com.course2go.service.place.PlaceService;
import com.course2go.service.user.UserService;

@Service
public class VisitServiceImpl implements VisitService {

	@Autowired
	VisitDao visitDao;
	@Autowired
	BoardService boardService;
	@Autowired
	PlaceService placeService;
	@Autowired
	ContainService containService;
	@Autowired
	UserService userService;
	
	@Override
	public void writeVisit(String uid, String title, VisitWriteDto dto, Integer rid) {
		int vid = writeVisit(uid, title, dto.getVisitPid(), dto.getVisitContent(), dto.getVisitTime(), dto.getVisitCost(), dto.getVisitImage1(), dto.getVisitImage2(), dto.getVisitImage3());
		containService.modifyContain(rid, dto.getVisitPid(), vid);
	}
	
	@Override
	public void writeVisit(String uid, String title, VisitWriteDto dto) {
		writeVisit(uid, title, dto.getVisitPid(), dto.getVisitContent(), dto.getVisitTime(), dto.getVisitCost(), dto.getVisitImage1(), dto.getVisitImage2(), dto.getVisitImage3());
	}

	@Override
	public Integer writeVisit(String uid, String title, Integer visitPid, String visitContent, Integer visitTime, Integer visitCost, String visitImage1, String visitImage2, String visitImage3) {
		int boardvid = visitDao.save(Visit.builder(visitPid, visitContent, visitTime, visitCost, visitImage1, visitImage2, visitImage3).build()).getVid();
		boardService.writeBoard(uid, title, 0, 0, boardvid, false);
		return boardvid;
	}

	@Override
	public VisitReadResponse readVisitBoard(Integer bid) {
		VisitReadResponse visitReadResponse = new VisitReadResponse();
		visitReadResponse.setBoardResponse(boardService.readBoard(bid));
		visitReadResponse.setVisitResponse(readVisit(visitReadResponse.getBoardResponse().getBoardTid()));
		visitReadResponse.setPlace(placeService.getPlace(visitReadResponse.getVisitResponse().getVisitPid()));
		visitReadResponse.setExtraData(new ExtraData(userService.getUserNicknameByUid(visitReadResponse.getBoardResponse().getBoardWriterUid())));
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

	@Override
	public List<VisitReadResponse> getMyVisitList(String uid) {
		List<VisitReadResponse> visitList = new LinkedList<VisitReadResponse>();
		List<BoardDto> list = boardService.getListbyUid(uid);
		for (BoardDto boardDto : list) {
			VisitReadResponse visitReadResponse = new VisitReadResponse();
			visitReadResponse.setBoardResponse(new BoardResponse(boardDto.getBoardWriterUid(), boardDto.getBoardTitle(), boardDto.getBoardLike(), boardDto.getBoardStar(), boardDto.getBoardTid(), boardDto.isBoardType(), boardDto.getBoardTime()));
			visitReadResponse.setVisitResponse(readVisit(boardDto.getBoardTid()));
			visitReadResponse.setPlace(placeService.getPlace(visitReadResponse.getVisitResponse().getVisitPid()));
			visitList.add(visitReadResponse);
		}
		return visitList;
	}

	@Override
	public List<VisitReadResponse> getVisitList(String userNickname) {
		return getMyVisitList(userService.getUidByUserNickname(userNickname));
	}
}
