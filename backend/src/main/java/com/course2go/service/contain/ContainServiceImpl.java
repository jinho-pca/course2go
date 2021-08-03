package com.course2go.service.contain;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.ContainDao;
import com.course2go.model.contain.Contain;
import com.course2go.model.place.PlaceDto;
import com.course2go.model.route.RouteSpot;
import com.course2go.service.place.PlaceService;
import com.course2go.service.visit.VisitService;

@Service
public class ContainServiceImpl implements ContainService {
	
	@Autowired
	ContainDao containDao;
	@Autowired
	PlaceService placeService;
	@Autowired
	VisitService visitService;

	@Override
	public void writeContain(int containRid, int containPid, Integer containVid, int containOrder) {
		containDao.save(Contain.builder(containRid, containPid, containVid, containOrder).build());
	}

	@Override
	public List<RouteSpot> listContain(Integer rid) {
		List<RouteSpot> spotList = new LinkedList<RouteSpot>();
		List<Contain> containList = containDao.findAllByContainRid(rid);
		for (Contain contain : containList) {
			spotList.add(new RouteSpot(placeService.getPlace(contain.getContainPid()), visitService.getVisit(contain.getContainVid())));
		}
		return spotList;
	}

}
