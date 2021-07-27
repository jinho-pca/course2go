package com.course2go.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course2go.model.visit.Visit;

public interface VisitDao extends JpaRepository<Visit,Integer> {

}
