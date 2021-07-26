package com.course2go.service.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.BoardDao;
import com.course2go.model.board.Board;
import com.course2go.model.board.BoardResponse;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao boardDao;
	
	@Override
	public void writeBoard(String boardWriterUid, Integer boardLike , Integer boardStar , Integer boardTid , boolean boardType) {
		boardDao.save(Board.builder(boardWriterUid, boardLike, boardStar, boardTid, boardType).build());
	}

	@Override
	public BoardResponse readBoard(int bid) {
		System.out.println(bid);
		System.out.println(boardDao.findAllByBid(bid));
		Board board = boardDao.findById(bid).get();
		BoardResponse boardResponse = new BoardResponse();
		boardResponse.setBoardLike(board.getBoardLike());
		boardResponse.setBoardStar(board.getBoardStar());
		boardResponse.setBoardTid(board.getBoardTid());
		boardResponse.setBoardType(board.isBoardType());
		boardResponse.setBoardWriterUid(board.getBoardWriterUid());
		boardResponse.setBoardTime(board.getBoardTime());
		return boardResponse;
	}
}
