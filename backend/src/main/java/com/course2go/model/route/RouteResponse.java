package com.course2go.model.route;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteResponse {
    private LocalDate routeStartDate;
    private LocalDate routeEndDate;
    private String routeContent;
}
