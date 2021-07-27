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
    
    public static VisitBuilder builder(Integer visitPid, String visitContent) {
		return VisitBuilder()
				.visitPid(visitPid)
				.visitContent(visitContent);
	}
}
