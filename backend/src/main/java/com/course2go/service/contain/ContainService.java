package com.course2go.service.contain;

import java.util.List;

import com.course2go.model.route.RouteSpot;

public interface ContainService {
	public void writeContain(int containRid, int containPid, Integer containVid, int containOrder);

	public List<RouteSpot> listContain(Integer rid);

	void modifyContain(int containRid, int containPid, Integer containVid);
	
	public List<Integer> getRidsContainPids(List<Integer> pids);
	
	public List<Integer> filterRidsWithExtraPid(List<Integer> rids, Integer pid);
	
	public void deleteContains(Integer rid);
	public void deleteContain(Integer vid);
}
