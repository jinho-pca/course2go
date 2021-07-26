package com.course2go.service.board;

import com.course2go.model.board.BoardResponse;

public interface BoardService {
	public void writeBoard(String boardWriterUid,Integer boardLike ,Integer boardStar ,Integer boardTid , boolean boardType);
	public BoardResponse readBoard(int bid);
}
