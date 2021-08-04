package com.course2go.model.newsfeed;

import com.course2go.model.board.BoardDto;
import com.course2go.model.comment.CommentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsfeedDto {
	private BoardDto board;
	private Object thing;
	private CommentDto bestcomment;
}
