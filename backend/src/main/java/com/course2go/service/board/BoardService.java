package com.course2go.service.board;

import java.util.List;

import com.course2go.model.board.BoardDto;
import com.course2go.model.board.BoardMyList;
import com.course2go.model.board.BoardResponse;

public interface BoardService {
	public void writeBoard(String boardWriterUid, String boardTitle, Integer boardLike, Integer boardStar, Integer boardTid, boolean boardType);
	public BoardResponse readBoard(int bid, String uid);
	public List<BoardDto> getListbyUids(Iterable<String> uids);
	public List<BoardDto> getListbyUid(String uid);
	public BoardMyList getMyList(String uid);
	public BoardResponse getBoardVisit(Integer vid, String uid);
	public BoardResponse getBoardRoute(Integer rid, String uid);
}
