package com.course2go.model.route;

import java.time.LocalDate;

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
@Builder(builderMethodName = "RouteBuilder")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;
    
    private LocalDate routeStartDate;
    private LocalDate routeEndDate;
    private String routeContent;
    private String routeImage;
    
    public static RouteBuilder builder(LocalDate routeStartDate, LocalDate routeEndDate, String routeContent, String routeImage) {
		return RouteBuilder()
				.routeStartDate(routeStartDate)
				.routeEndDate(routeEndDate)
				.routeContent(routeContent)
				.routeImage(routeImage);
	}
}
