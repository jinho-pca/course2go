package com.course2go.service.user;

import com.course2go.model.user.User;

public interface UserProfileModifyService {
	
	// 프로필 수정
	public int userProfileModify(String tokenEmail, String requestComment, String imageUrl);
}
