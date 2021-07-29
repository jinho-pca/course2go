package com.course2go.model.comment;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentWriteRequest {
    private Integer commentParent;
    private Integer commentBid;
    private String commentContent;
}
