package com.course2go.service.place;

import java.util.List;

import com.course2go.model.place.PlaceDto;

public interface PlaceService {
	public List<PlaceDto> listPlaceInRoute(Iterable<Integer> pids);
	public List<PlaceDto> searchPlace(String word);
	public PlaceDto getPlace(Integer pid);
	public List<PlaceDto> searchPlaceByType(String type);
}
