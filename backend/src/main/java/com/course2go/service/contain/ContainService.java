package com.course2go.service.contain;

import java.util.List;

import com.course2go.model.place.PlaceDto;
import com.course2go.model.route.RouteSpot;

public interface ContainService {
	public void writeContain(int containRid, int containPid, Integer containVid, int containOrder);

	public List<RouteSpot> listContain(Integer rid);
}
