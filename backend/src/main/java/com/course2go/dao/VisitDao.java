package com.course2go.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course2go.model.route.Route;
import com.course2go.model.visit.Visit;

public interface VisitDao extends JpaRepository<Visit,Integer> {
	
	public List<Visit> findAllByVisitPid(Integer pid);
	public Visit findVisitByVid(Integer vid); 
}
