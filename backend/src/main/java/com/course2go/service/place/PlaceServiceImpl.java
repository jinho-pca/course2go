package com.course2go.service.place;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.PlaceDao;
import com.course2go.model.place.Place;
import com.course2go.model.place.PlaceDto;

@Service
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	PlaceDao placeDao;
	
	ModelMapper modelmapper;

	public PlaceServiceImpl() {
		modelmapper = new ModelMapper();
	}
	@Override
	public List<PlaceDto> listPlaceInRoute(Iterable<Integer> pids) {
		List<Place> placeList = placeDao.findAllById(pids);
		return placeList.stream().map(entityList -> modelmapper.map(entityList, PlaceDto.class)).collect(Collectors.toList());
	}
	@Override
	public List<PlaceDto> searchPlace(String word) {
//		word = "%" + word + "%"; // 프론트에서 해오는게 나을 듯. js가 문자열은 더 좋으니
		return placeDao.findAllByPlaceNameLike(word).stream().map(place -> modelmapper.map(place, PlaceDto.class)).collect(Collectors.toList());
	}
}
