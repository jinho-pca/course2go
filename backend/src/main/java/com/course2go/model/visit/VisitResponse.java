package com.course2go.model.visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitResponse {
    private Integer visitPid;
    private String visitContent;
    private Integer visitTime;
    private Integer visitCost;
    private String visitImage1;
    private String visitImage2;
    private String visitImage3;
}
