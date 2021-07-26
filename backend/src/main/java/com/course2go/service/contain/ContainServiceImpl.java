package com.course2go.service.contain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.ContainDao;
import com.course2go.model.contain.Contain;

@Service
public class ContainServiceImpl implements ContainService {
	
	@Autowired
	ContainDao containDao;

	@Override
	public void writeContain(int containRid, int containPid, int containOrder) {
		containDao.save(new Contain(containRid,containPid,containOrder));
	}

}
