package com.course2go.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.course2go.model.user.User;
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

}