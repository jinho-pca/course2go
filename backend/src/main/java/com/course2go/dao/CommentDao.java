package com.course2go.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course2go.model.comment.Comment;

public interface CommentDao extends JpaRepository<Comment,Integer> {
	
	List<Comment> findAllByCommentBid(Integer commentBid);
	List<Comment> findAllByCommentBidOrderByCommentLikeDesc(Integer commentBid);
	
}
