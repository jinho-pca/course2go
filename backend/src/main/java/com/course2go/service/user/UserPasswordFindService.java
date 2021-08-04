package com.course2go.service.user;

import com.course2go.model.user.UserEmailRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

public interface UserPasswordFindService {
	
	// 비밀번호 찾기
	public int userPasswordFind(@RequestBody UserEmailRequest userEmailRequest);
}
