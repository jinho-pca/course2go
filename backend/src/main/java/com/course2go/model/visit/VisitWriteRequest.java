package com.course2go.model.visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitWriteRequest {
	private String title;
    private Integer visitPid;
    private String visitContent;
    private Integer visitTime;
    private Integer visitCost;
}
