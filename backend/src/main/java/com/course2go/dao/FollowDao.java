package com.course2go.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.course2go.model.follow.Follow;
import com.course2go.model.user.User;


public interface FollowDao extends JpaRepository<Follow, String>{
	
	@Query(
			value = "SELECT uid, user_name, user_email, user_password ,user_nickname, user_salt, user_birthday, user_gender, user_comment, user_image" +
					" FROM follow f " + 
					" JOIN user u ON f.follow_to_uid = u.uid" +
					" WHERE f.follow_to_uid = :uid"
			, nativeQuery = true
			)
	List<User> getFollowers(@Param("uid") String uid); 
	
	
	@Query(value = "SELECT * FROM follow", nativeQuery = true)
	List<Follow> get();
}
