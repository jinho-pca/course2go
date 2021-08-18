package com.course2go.service.visit;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

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
import com.course2go.service.boardlike.BoardlikeService;
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
	BoardlikeService boardlikeService;
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
	public VisitReadResponse readVisitBoard(Integer vid, String uid) {
		
		int bid = boardService.getBidByTidAndBoardType(vid, false);
		
		VisitReadResponse visitReadResponse = new VisitReadResponse();
		visitReadResponse.setBoardResponse(boardService.readBoard(bid, uid));
		visitReadResponse.setVisitResponse(readVisit(visitReadResponse.getBoardResponse().getBoardTid()));
		visitReadResponse.setPlace(placeService.getPlace(visitReadResponse.getVisitResponse().getVisitPid()));
		visitReadResponse.setUserDto(userService.getUserDtoByUid(visitReadResponse.getBoardResponse().getBoardWriterUid()));
		return visitReadResponse;
	}

	@Override
	public VisitResponse readVisit(Integer vid) {
		Visit visit = visitDao.findVisitByVid(vid);
		return new VisitResponse(visit.getVid(),visit.getVisitPid(), visit.getVisitContent(), visit.getVisitTime(), visit.getVisitCost(), visit.getVisitImage1(), visit.getVisitImage2(), visit.getVisitImage3());
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
	public List<VisitReadResponse> getMyVisitList(String uid1, String uid) {
		List<VisitReadResponse> visitList = new LinkedList<VisitReadResponse>();
		List<BoardDto> list = boardService.getListbyUid(uid1);
		for (BoardDto boardDto : list) {
			if(boardDto.isBoardType()) {
				continue;
			}
			VisitReadResponse visitReadResponse = new VisitReadResponse();
			visitReadResponse.setBoardResponse(new BoardResponse(boardDto.getBid(),boardDto.getBoardWriterUid(), boardDto.getBoardTitle(), boardDto.getBoardLike(), boardDto.getBoardStar(), boardDto.getBoardTid(), boardDto.isBoardType(), boardDto.getBoardTime(), boardlikeService.iLike(uid, boardDto.getBid())));
			visitReadResponse.setVisitResponse(readVisit(boardDto.getBoardTid()));
			visitReadResponse.setPlace(placeService.getPlace(visitReadResponse.getVisitResponse().getVisitPid()));
			visitReadResponse.setUserDto(userService.getUserDtoByUid(visitReadResponse.getBoardResponse().getBoardWriterUid()));
			visitList.add(visitReadResponse);
		}
		return visitList;
	}

	@Override
	public List<VisitReadResponse> getVisitList(String userNickname, String uid) {
		return getMyVisitList(userService.getUidByUserNickname(userNickname), uid);
	}

	@Override
	public List<VisitReadResponse> getVisitListByPid(Integer pid, String uid) {
		List<VisitReadResponse> visitList = new LinkedList<VisitReadResponse>();
		List<Visit> list = visitDao.findAllByVisitPid(pid);
		for (Visit visit : list) {
			VisitReadResponse visitReadResponse = new VisitReadResponse();
			visitReadResponse.setBoardResponse(boardService.getBoardVisit(visit.getVid(), uid));
			visitReadResponse.setVisitResponse(new VisitResponse(visit.getVid(),visit.getVisitPid(), visit.getVisitContent(), visit.getVisitTime(), visit.getVisitCost(), visit.getVisitImage1(), visit.getVisitImage2(), visit.getVisitImage3()));
			visitReadResponse.setPlace(placeService.getPlace(pid));
			visitReadResponse.setUserDto(userService.getUserDtoByUid(visitReadResponse.getBoardResponse().getBoardWriterUid()));
			visitList.add(visitReadResponse);
		}
		return visitList;
	}

	@Transactional
	@Override
	public void deleteVisit(Integer vid) {
		containService.deleteVisitInContain(vid);
		visitDao.deleteById(vid);
	}
}
