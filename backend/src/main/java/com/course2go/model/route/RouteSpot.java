package com.course2go.model.route;

import com.course2go.model.place.PlaceDto;
import com.course2go.model.visit.VisitDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteSpot {
	private PlaceDto place;
	private VisitDto visit;
}
