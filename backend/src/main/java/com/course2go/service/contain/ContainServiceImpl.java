package com.course2go.service.contain;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

	ModelMapper modelmapper;
	
	public ContainServiceImpl() {
		modelmapper = new ModelMapper();
	}
	
	@Override
	public void writeContain(int containRid, int containPid, Integer containVid, int containOrder) {
		containDao.save(Contain.builder(containRid, containPid, containVid, containOrder).build());
	}

	@Override
	public void modifyContain(int containRid, int containPid, Integer containVid) {
		Optional<Contain> contain = containDao.findContainByContainRidAndContainPid(containRid, containPid);
		containDao.save(new Contain(contain.get().getId(),containRid, containPid, containVid,contain.get().getContainOrder()));
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

	@Override
	public List<Integer> getRidsContainPids(List<Integer> pids) {
		List<Contain> containList = containDao.findAllByContainPid(pids.get(0));
		List<Integer> rids = containList.stream().map(contain -> modelmapper.map(contain.getContainRid(), Integer.class)).collect(Collectors.toList());
		
		for (int i = 1; i < pids.size(); i++) {
			rids = filterRidsWithExtraPid(rids, pids.get(i));
		}
		return rids;
	}

	@Override
	public List<Integer> filterRidsWithExtraPid(List<Integer> rids, Integer pid) {
		List<Contain> containList = containDao.findAllByContainRidInAndContainPid(rids, pid);
		List<Integer> rids2 = containList.stream().map(contain -> modelmapper.map(contain.getContainRid(), Integer.class)).collect(Collectors.toList()); 
		return rids2;
	}

	@Override
	public void deleteContains(Integer rid) {
		containDao.deleteAllByContainRid(rid);
	}

	@Override
	public void deleteContain(Integer vid) {
		containDao.deleteAllByContainVid(vid);
	}

}
