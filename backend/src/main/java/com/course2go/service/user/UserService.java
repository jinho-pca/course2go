package com.course2go.service.user;

import java.util.Optional;

import com.course2go.model.user.User;

public interface UserService {
	Optional<User> getUserByUserNickname(String nickname);
}
