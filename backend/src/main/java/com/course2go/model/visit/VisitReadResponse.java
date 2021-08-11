package com.course2go.model.visit;

import com.course2go.model.ExtraData;
import com.course2go.model.board.BoardResponse;
import com.course2go.model.place.PlaceDto;
import com.course2go.model.user.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitReadResponse {
	private VisitResponse visitResponse;
	private BoardResponse boardResponse;
	private PlaceDto place;
	private UserDto userDto;
}
