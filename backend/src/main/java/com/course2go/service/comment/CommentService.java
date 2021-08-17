package com.course2go.service.comment;

import java.util.List;

import com.course2go.model.comment.CommentDto;
import com.course2go.model.comment.CommentWriteRequest;

public interface CommentService {
	public void writeComment(String commentWriterUid, CommentWriteRequest request);
	public void writeComment(Integer commentParent, Integer commentBid, Integer commentLike, String commentContent, String commentWriterUid);
	public List<CommentDto> readComment(Integer commentBid);
	public List<CommentDto> readSortedComment(Integer commentBid);
	public List<CommentDto> readSortedCommentByVid(Integer Vid);
	public CommentDto getCommentBiggestLike(Integer commentBid);
	public void deleteComment(Integer cid);
}
