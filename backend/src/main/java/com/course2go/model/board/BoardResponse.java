package com.course2go.model.board;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponse {
    private String boardWriterUid;
    private String boardTitle;
    private Integer boardLike;
    private Integer boardStar;
    private Integer boardTid;
    private boolean	boardType;
    private LocalDateTime boardTime;
    private boolean	boardILike;
    
    public BoardResponse(Board board, boolean boardILike) {
		this.boardLike = board.getBoardLike();
		this.boardStar = board.getBoardStar();
		this.boardTid = board.getBoardTid();
		this.boardType = board.isBoardType();
		this.boardTitle = board.getBoardTitle();
		this.boardWriterUid = board.getBoardWriterUid();
		this.boardTime = board.getBoardTime();
		this.boardILike = boardILike;
    }
}
