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
	
	

}
