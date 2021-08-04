package com.course2go.model.visit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "VisitBuilder")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vid;
    
    private Integer visitPid;
    private String visitContent;
    private Integer visitTime;
    private Integer visitCost;
    
    private String visitImage1;
    private String visitImage2;
    private String visitImage3;
    
    public static VisitBuilder builder(Integer visitPid, String visitContent, Integer visitTime, Integer visitCost, String visitImage1, String visitImage2, String visitImage3) {
		return VisitBuilder()
				.visitPid(visitPid)
				.visitContent(visitContent)
				.visitTime(visitTime)
				.visitCost(visitCost)
				.visitImage1(visitImage1)
				.visitImage2(visitImage2)
				.visitImage3(visitImage3);
	}
}
