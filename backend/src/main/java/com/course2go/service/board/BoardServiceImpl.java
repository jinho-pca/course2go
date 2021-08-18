package com.course2go.service.board;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

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
import com.course2go.service.boardlike.BoardlikeService;
import com.course2go.service.comment.CommentService;
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
	@Autowired
	CommentService commentService;
	@Autowired
	BoardlikeService likeService;
	
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
	public BoardResponse readBoard(int bid, String uid) {
		logger.info(""+bid);
		logger.info(""+boardDao.findAllByBid(bid));
		Board board = boardDao.findById(bid).get();
		return getBoard(board, uid);
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
				mylist.addroute(routeService.readRouteBoard(board.getBid(), uid));
			} else {
				mylist.addvisit(visitService.readVisitBoard(board.getBid(), uid));
			}
		}
		return mylist;
	}

	@Override
	public BoardResponse getBoardVisit(Integer vid, String uid) {
		List<Board> list = boardDao.findAllByBoardTypeAndBoardTid(false, vid);
		if (list.size()==0) {
			return null;
		}
		Board board = list.get(0);
		return getBoard(board, uid);
	}

	@Override
	public BoardResponse getBoardRoute(Integer rid, String uid) {
		List<Board> list = boardDao.findAllByBoardTypeAndBoardTid(true, rid);
		if (list.size()==0) {
			return null;
		}
		Board board = list.get(0);
		return getBoard(board, uid);
	}

	private BoardResponse getBoard(Board board, String uid) {
		return new BoardResponse(board,likeService.iLike(uid, board.getBid()));
	}

	@Override
	public Integer getBidByTidAndBoardType(Integer tid, Boolean boardType) {
		return boardDao.findBoardByBoardTidAndBoardType(tid, boardType).getBid();
	}

	@Override
	public boolean isMyBoard(Integer bid, String uid) {
		Optional<Board> boardOpt = boardDao.findById(bid);
		if (boardOpt.get().getBoardWriterUid().equals(uid)) {
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public void deleteBoard(Integer bid) {
		Board board = boardDao.findById(bid).get();
		likeService.deleteBoardlike(bid);
		commentService.deleteCommentsByBid(bid);
		if (board.isBoardType()) { // 동선
			routeService.deleteRoute(board.getBoardTid());
		}
		visitService.deleteVisit(board.getBoardTid());
		boardDao.deleteById(bid);
	}
}
