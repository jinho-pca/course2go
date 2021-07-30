package com.course2go.service.user;

import java.io.InputStream;
import java.time.LocalDate;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.course2go.dao.UserDao;
import com.course2go.model.user.User;
import com.course2go.model.user.UserEmailResponse;

@Service
public class UserPasswordFindServiceImpl implements UserPasswordFindService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JavaMailSender mailSender;
	private static final String FROM_ADDRESS = "ssafy.course2go@gmail.com";
	
	@Override
	public int userPasswordFind(String userEmail, String userNickname, LocalDate userBirthday) {
		
		System.out.println(userEmail);
		System.out.println(userNickname);
		System.out.println(userBirthday);
		
		// 임시비밀번호 전송을 요청한 유저가 존재하는 지 체크
		if(userDao.findUserByUserEmailAndUserNicknameAndUserBirthday(userEmail, userNickname, userBirthday).isPresent()) {
			// 임시비밀번호 전송을 요청한 유저가 존재하는 경우
			User user = userDao.findUserByUserEmailAndUserNicknameAndUserBirthday(userEmail, userNickname, userBirthday).get();
			
			// 임시 비밀번호 발급
			String tmpPassword = getTempPassword();
			
			// 이메일 생성
			UserEmailResponse result = createEmail(userEmail, userNickname, userBirthday, tmpPassword);
			
			// 임시 비밀번호로 회원정보 수정
			user.setUserPassword(tmpPassword);
			userDao.save(user);
			
			// 이메일 전송
			mailSend(result);
			
			return 1;
			
		}else return 0;
		
	}

	public UserEmailResponse createEmail(String userEmail, String userNickname, LocalDate userBirthday, String tmpPassword) {
		UserEmailResponse result = new UserEmailResponse();
		result.setEmail(userEmail);
		result.setTitle(userNickname + "님의 Course2Go 임시 비밀번호 안내 관련 메일 입니다.");
		result.setMessage("안녕하세요. Course2Go 임시 비밀번호 안내 관련 메일 입니다." + "[" + userNickname + "]님의 임시 비밀번호는 " + tmpPassword + " 입니다.");
		
		return result;
	}
	
	public String getTempPassword() {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		
		String str = "";
		
		int idx = 0;
		
		for (int i = 0; i < 10; i++) {
			idx = (int) (charSet.length * Math.random());
			str += charSet[idx];
		}
		str += "!@";
		return str;
	}
	
	public void mailSend(UserEmailResponse dto) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(dto.getEmail());
		message.setFrom(FROM_ADDRESS);
		message.setSubject(dto.getTitle());
		message.setText(dto.getMessage());
		
		System.out.println(message.toString());
		
		mailSender.send(message);
	}
}
