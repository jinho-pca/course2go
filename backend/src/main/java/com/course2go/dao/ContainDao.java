package com.course2go.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course2go.model.contain.Contain;
import com.course2go.model.place.PlaceDto;

public interface ContainDao extends JpaRepository<Contain, Integer> {

	List<Contain> findAllByContainRid(Integer containRid);

}
