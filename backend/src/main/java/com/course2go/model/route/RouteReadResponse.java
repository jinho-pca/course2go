package com.course2go.model.route;

import java.util.List;

import com.course2go.model.board.BoardResponse;
import com.course2go.model.place.PlaceDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteReadResponse {
	private RouteResponse routeResponse;
	private BoardResponse boardResponse;
	private List<PlaceDto> containPlaces;
}
