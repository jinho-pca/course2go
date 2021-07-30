package com.course2go.model.route;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteWriteRequest {
    private LocalDate routeStartDate;
    private LocalDate routeEndDate;
    private String routeContent;
    private List<Integer> routePid;
}
