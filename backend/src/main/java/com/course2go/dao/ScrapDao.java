package com.course2go.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course2go.model.follow.Follow;

public interface ScrapDao extends JpaRepository<Follow, Integer>{

}
