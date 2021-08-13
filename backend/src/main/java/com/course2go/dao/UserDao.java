package com.course2go.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.course2go.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface UserDao extends JpaRepository<User, String> {

	Optional<User> findUserByUserEmailAndUserPassword(String userEmail, String userPassword);

	Optional<User> findUserByUserEmail(String userEmail);

	Optional<User> findUserByUserNickname(String userNickname);

	Optional<User> findUserByUserNicknameAndUserBirthday(String userNickname, LocalDate userBirthday);

	Optional<User> findUserByUserEmailAndUserNicknameAndUserBirthday(String userEmail, String userNickname, LocalDate userBirthday);
	
	boolean existsByUserEmail(String userEmail);

	boolean existsByUserNickname(String userNickname);

	Optional<User> getUserByUserEmail(String userEmail);

	Optional<User> getUserByUserNickname(String userNickname);

	List<User> findAllByUserName(String userName);

	List<User> findByUserNameContaining(String keyword);

	List<User> findByUserNicknameContaining(String keyword);
	
	@Query(
			value = "SELECT u.uid, u.user_name, u.user_email, u.user_password, u.user_nickname, u.user_salt, u.user_birthday, u.user_gender, u.user_image, u.user_comment" +
					" FROM follow f " + 
					" JOIN user u ON f.follow_from_uid = u.uid" +
					" WHERE f.follow_to_uid = :uid"
			, nativeQuery = true
			)
	List<User> getFollowings(@Param("uid") String uid); 
	
	@Query(
			value = "SELECT u.uid, u.user_name, u.user_email, u.user_password, u.user_nickname, u.user_salt, u.user_birthday, u.user_gender, u.user_image, u.user_comment" +
					" FROM follow f " + 
					" JOIN user u ON f.follow_to_uid = u.uid" +
					" WHERE f.follow_from_uid = :uid"
			, nativeQuery = true
			)
	List<User> getFollowers(@Param("uid") String uid);

	@Query(
			value = "SELECT *" +
					" FROM user" +
					" WHERE user_name LIKE %:keyword%" +
					" OR user_nickname LIKE %:keyword% "
			, nativeQuery = true
	)
	List<User> findByUserNameOrUserNicknameContaining(String keyword);
}