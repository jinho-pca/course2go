package com.course2go.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course2go.model.boardlike.Boardlike;

public interface BoardlikeDao extends JpaRepository<Boardlike, Integer>{
	boolean existsByLikeUidAndLikeBid(String likeUid, Integer likeBid);
	List<Boardlike> findAllByLikeUidAndLikeBid(String likeUid, Integer likeBid);
	Optional<Boardlike> findBoardlikeByLikeUidAndLikeBid(String likeUid, Integer likeBid);
	void deleteAllByLikeBid(Integer likeBid);
}
