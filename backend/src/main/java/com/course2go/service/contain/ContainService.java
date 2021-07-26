package com.course2go.service.contain;

import java.util.List;

import com.course2go.model.place.PlaceDto;

public interface ContainService {
	public void writeContain(int containRid, int containPid, int containOrder);

	public List<PlaceDto> listContain(Integer rid);
}
