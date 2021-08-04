package com.course2go.model.contain;

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
@Builder(builderMethodName = "ContainBuilder")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer containRid;
    private Integer containPid;
    private Integer containVid;
    private Integer containOrder;
    
    public static ContainBuilder builder(Integer containRid, Integer containPid, Integer containVid, Integer containOrder) {
		return ContainBuilder()
				.containRid(containRid)
				.containPid(containPid)
				.containVid(containVid)
				.containOrder(containOrder);
	}
}
