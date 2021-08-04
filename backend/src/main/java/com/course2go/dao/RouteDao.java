package com.course2go.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course2go.model.route.Route;

public interface RouteDao extends JpaRepository<Route, Integer> {
}
