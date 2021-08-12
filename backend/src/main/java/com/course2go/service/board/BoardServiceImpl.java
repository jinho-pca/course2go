package com.course2go.service.board;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.authentication.TokenUtils;
import com.course2go.dao.BoardDao;
import com.course2go.model.board.Board;
import com.course2go.model.board.BoardDto;
import com.course2go.model.board.BoardMyList;
import com.course2go.model.board.BoardResponse;
import com.course2go.service.route.RouteService;
import com.course2go.service.visit.VisitService;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao boardDao;
	@Autowired
	RouteService routeService;
	@Autowired
	VisitService visitService;
	
	private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);
	
	ModelMapper modelmapper;
	public BoardServiceImpl() {
		modelmapper = new ModelMapper();
	}
	
	@Override
	public void writeBoard(String boardWriterUid, String boardTitle, Integer boardLike , Integer boardStar , Integer boardTid , boolean boardType) {
		boardDao.save(Board.builder(boardWriterUid, boardTitle, boardLike, boardStar, boardTid, boardType).build());
	}

	@Override
	public BoardResponse readBoard(int bid) {
		logger.info(""+bid);
		logger.info(""+boardDao.findAllByBid(bid));
		Board board = boardDao.findById(bid).get();
		BoardResponse boardResponse = new BoardResponse();
		boardResponse.setBoardLike(board.getBoardLike());
		boardResponse.setBoardStar(board.getBoardStar());
		boardResponse.setBoardTid(board.getBoardTid());
		boardResponse.setBoardType(board.isBoardType());
		boardResponse.setBoardTitle(board.getBoardTitle());
		boardResponse.setBoardWriterUid(board.getBoardWriterUid());
		boardResponse.setBoardTime(board.getBoardTime());
		return boardResponse;
	}

	@Override
	public List<BoardDto> getListbyUids(Iterable<String> uids) {
		List<Board> list = boardDao.findAllByBoardWriterUidIn(uids);
		return list.stream().map(board -> modelmapper.map(board, BoardDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<BoardDto> getListbyUid(String uid) {
		List<Board> list = boardDao.findAllByBoardWriterUid(uid);
		System.out.println(list.toString());
		return list.stream().map(board -> modelmapper.map(board, BoardDto.class)).collect(Collectors.toList());
	}

	@Override
	public BoardMyList getMyList(String uid) {
		List<Board> list = boardDao.findAllByBoardWriterUid(uid);
		BoardMyList mylist = new BoardMyList();
		for (Board board : list) {
			if (board.isBoardType()) {
				mylist.addroute(routeService.readRouteBoard(board.getBid()));
			} else {
				mylist.addvisit(visitService.readVisitBoard(board.getBid()));
			}
		}
		return mylist;
	}

	@Override
	public BoardResponse getBoardVisit(Integer vid) {
		List<Board> list = boardDao.findAllByBoardTypeAndBoardTid(false, vid);
		if (list.size()==0) {
			return null;
		}
		Board board = list.get(0);
		return new BoardResponse(board.getBoardWriterUid(), board.getBoardTitle(), board.getBoardLike(), board.getBoardStar(), board.getBoardTid(), board.isBoardType(), board.getBoardTime());
	}

	@Override
	public BoardResponse getBoardRoute(Integer rid) {
		List<Board> list = boardDao.findAllByBoardTypeAndBoardTid(true, rid);
		if (list.size()==0) {
			return null;
		}
		Board board = list.get(0);
		return new BoardResponse(board.getBoardWriterUid(), board.getBoardTitle(), board.getBoardLike(), board.getBoardStar(), board.getBoardTid(), board.isBoardType(), board.getBoardTime());
	}
}
