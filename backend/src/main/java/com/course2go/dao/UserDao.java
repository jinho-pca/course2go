package com.course2go.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.course2go.model.user.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {
	
	Optional<User> findUserByUserEmailAndUserPassword(String userEmail, String userPassword);
	
	boolean existsByUserEmail(String userEmail);
	
	boolean existsByUserNickname(String userNickname);
	
	User getUserByUserEmail(String userEmail);
	
	@Query(
			value = "SELECT u.uid, u.user_name, u.user_email, u.user_password ,u.user_nickname, u.user_salt, u.user_birthday, u.user_gender, u.user_comment, u.user_image" +
					" FROM follow f " + 
					" JOIN user u ON f.follow_from_uid = u.uid" +
					" WHERE f.follow_to_uid = :uid"
			, nativeQuery = true
			)
	List<User> getFollowers(@Param("uid") String uid); 
	
	@Query(
			value = "SELECT u.uid, u.user_name, u.user_email, u.user_password ,u.user_nickname, u.user_salt, u.user_birthday, u.user_gender, u.user_comment, u.user_image" +
					" FROM follow f " + 
					" JOIN user u ON f.follow_to_uid = u.uid" +
					" WHERE f.follow_from_uid = :uid"
			, nativeQuery = true
			)
	List<User> getFollowings(@Param("uid") String uid); 
	
	
}