package com.course2go.model.comment;

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
@Builder(builderMethodName = "CommentBuilder")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    
    private Integer commentParent;
    private Integer commentBid;
    private Integer commentLike;
    private String commentContent;
    private String commentWriterUid;

    @Column(insertable = false, updatable = false)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private LocalDateTime commentTime;
    

    public static CommentBuilder builder(Integer commentParent, Integer commentBid, Integer commentLike, String commentContent, String commentWriterUid) {
		return CommentBuilder()
				.commentParent(commentParent)
				.commentBid(commentBid)
				.commentLike(commentLike)
				.commentContent(commentContent)
				.commentWriterUid(commentWriterUid);
	}
}
