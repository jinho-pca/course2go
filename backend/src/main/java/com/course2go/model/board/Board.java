package com.course2go.model.board;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "BoardBuilder")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bid;

    private String boardWriterUid;
    private String boardTitle;
    private Integer boardLike;
    private Integer boardStar;
    private Integer boardTid;
    private boolean	boardType;

    @Column(insertable = false, updatable = false)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private LocalDateTime boardTime;
    
    public void addLike() {
    	boardLike++;
    }
    
    public void minusLike() {
    	boardLike--;
    }
    
    public static BoardBuilder builder(String boardWriterUid, String boardTitle, Integer boardLike , Integer boardStar , Integer boardTid , boolean boardType) {
		return BoardBuilder()
				.boardWriterUid(boardWriterUid)
				.boardTitle(boardTitle)
				.boardLike(boardLike)
				.boardStar(boardStar)
				.boardTid(boardTid)
				.boardType(boardType);
	}
}
