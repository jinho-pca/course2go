package com.course2go.service.board;

import java.util.List;

import com.course2go.model.board.BoardDto;
import com.course2go.model.board.BoardResponse;

public interface BoardService {
	public void writeBoard(String boardWriterUid,Integer boardLike ,Integer boardStar ,Integer boardTid , boolean boardType);
	public BoardResponse readBoard(int bid);
	public List<BoardDto> getListbyUids(Iterable<String> uids);
}
