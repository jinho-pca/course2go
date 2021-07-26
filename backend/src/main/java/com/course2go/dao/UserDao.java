package com.course2go.dao;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.course2go.model.user.User;
import org.springframework.stereotype.Repository;

@Repository

public interface UserDao extends JpaRepository<User, String> {
	
	Optional<User> findUserByUserEmailAndUserPassword(String userEmail, String userPassword);
	
	boolean existsByUserEmail(String userEmail);
	
	boolean existsByUserNickname(String userNickname);

	User getUserByUserEmail(String userEmail);
	
}