package com.course2go.service.contain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.ContainDao;
import com.course2go.model.contain.Contain;
import com.course2go.model.place.PlaceDto;
import com.course2go.service.place.PlaceService;

@Service
public class ContainServiceImpl implements ContainService {
	
	@Autowired
	ContainDao containDao;
	@Autowired
	PlaceService placeService;

	@Override
	public void writeContain(int containRid, int containPid, int containOrder) {
		containDao.save(Contain.builder(containRid, containPid, containOrder).build());
	}

	@Override
	public List<PlaceDto> listContain(Integer rid) {
		List<Contain> containList = containDao.findAllByContainRid(rid);
		List<Integer> pidList = containList.stream().map(contain -> contain.getContainPid()).collect(Collectors.toList());
		List<PlaceDto> placeList = placeService.listPlaceInRoute(pidList);	
		return placeList;
	}

}
