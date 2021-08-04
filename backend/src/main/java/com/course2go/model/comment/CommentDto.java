package com.course2go.model.comment;

import java.time.LocalDateTime;

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
    private LocalDateTime commentTime;
}
