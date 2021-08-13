package com.course2go.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.course2go.model.comment.Comment;

public interface CommentDao extends JpaRepository<Comment,Integer> {
	
	List<Comment> findAllByCommentBid(Integer commentBid);
	List<Comment> findAllByCommentBidOrderByCommentLikeDesc(Integer commentBid);

	@Transactional
	@Modifying
	@Query( value = "UPDATE Comment c" +
					" SET c.comment_deleted = 1" + 
					" WHERE c.cid = :cid"
			, nativeQuery = true)
	void updateDeleted(Integer cid);

}
