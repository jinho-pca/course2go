package com.course2go.model.comment;

import java.time.LocalDateTime;

import com.course2go.model.user.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Integer cid;
    private Integer commentParent;
    private Integer commentBid;
    private Integer commentLike;
    private String commentContent;
    private String commentWriterUid;
    private boolean commentDeleted;
    private LocalDateTime commentTime;
    
    private Integer commentDepth;
    private UserDto commentWriterDto;
    
    public CommentDto(Comment comment, Integer commentDepth, UserDto commentWriterDto){
    	this(comment.getCid(), comment.getCommentParent(), comment.getCommentBid(), comment.getCommentLike(), comment.getCommentContent(), comment.getCommentWriterUid(), comment.isCommentDeleted(), comment.getCommentTime(), commentDepth, commentWriterDto);
    }
}
