package com.course2go.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course2go.model.board.Board;

public interface BoardDao extends JpaRepository<Board, Integer>  {
	public Board findAllByBid(Integer bid);
}
