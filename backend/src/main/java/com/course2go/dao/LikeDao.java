package com.course2go.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course2go.model.like.Like;

public interface LikeDao extends JpaRepository<Like, Integer>{
	boolean existsByLikeUidAndLikeBid(String likeUid, Integer likeBid);
	Optional<Like> findLikeByLikeUidAndLikeBid(String likeUid, Integer likeBid);
}
