package com.course2go.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.course2go.model.BasicResponse;
import com.course2go.model.user.SignupRequest;
import com.course2go.model.user.User;
import com.course2go.model.user.UserEmailFindRequest;
import com.course2go.model.user.UserEmailRequest;
import com.course2go.model.user.UserModifyRequest;
import com.course2go.model.user.UserProfileModifyRequest;
import com.course2go.service.image.S3Uploader;
import com.course2go.service.user.UserDeleteService;
import com.course2go.service.user.UserEmailFindService;
import com.course2go.service.user.UserModifyService;
import com.course2go.service.user.UserPasswordFindService;
import com.course2go.service.user.UserProfileModifyService;
import com.course2go.service.user.UserProfileService;
import com.course2go.service.user.UserRegisterService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@Api(value="user")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRegisterService userRegisterService;

    @PostMapping("/signup")
    @ApiOperation(value = "회원가입")
    public Object signup(@Valid @RequestBody SignupRequest request) {
    	
        final BasicResponse result = new BasicResponse();
        HttpStatus status = HttpStatus.CONFLICT;
        
        User user = new User();
        user.setUserName(request.getUserName());
        user.setUserEmail(request.getUserEmail());
        user.setUserPassword(request.getUserPassword());
        user.setUserNickname(request.getUserNickname());
        user.setUserBirthday(request.getUserBirthday());
        user.setUserGender(request.getUserGender());
        
        int registerResult = userRegisterService.userRegister(user);
        
        switch(registerResult) {
        // 이메일 중복
        case 0:
        	result.data = "email overlap";
        	result.status = false;
        	break;
        // 닉네임 중복
        case -1:
        	result.data = "nickname overlap";
        	result.status = false;
        	break;
        // 가입 완료
        case 1:
        	result.data = "success";
        	result.status = true;
        	status = HttpStatus.OK;
        	break;
        }
        
        return new ResponseEntity<>(result, status);
    }
    
    @Autowired
    UserModifyService userModifyService;
    
    @PutMapping("/modify")
    @ApiOperation("회원정보수정")
    public Object modify(@Valid @RequestBody UserModifyRequest request) {
    	final BasicResponse result = new BasicResponse();
    	HttpStatus status = HttpStatus.CONFLICT;
    	
    	User user = new User();
    	user.setUserEmail(request.getUserEmail());
    	user.setUserNickname(request.getUserNickname());
    	user.setUserPassword(request.getUserPassword());
    	    	
    	int modifyResult = userModifyService.userModify(user);
    	
    	switch(modifyResult) {
    	// // 회원정보 수정을 요청한 유저가 존재하지 않는 경우 
    	case -1:
    		result.data = "unpresent user";
    		result.status = false;
    		status = HttpStatus.BAD_REQUEST;
    		break;
    	// 닉네임 중복
    	case 0:
    		result.data = "nickname overlap";
    		result.status = false;
    		break;
    	// 회원정보 수정 완료
    	case 1:
    		result.data = "success";
    		result.status = true;
    		status = HttpStatus.OK;
    		break;
    	}
    	
    	return new ResponseEntity<>(result, status);
    }
    
    @Autowired
    UserDeleteService userDeleteService;
    
    @DeleteMapping("/delete/{userNickname}")
    @ApiOperation("회원탈퇴")
    public Object delete(@PathVariable("userNickname") String userNickname) {
    	final BasicResponse result = new BasicResponse();
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	
    	int deleteResult = userDeleteService.userDelete(userNickname);
    	
    	switch(deleteResult) {
    	// 회원탈퇴 요청한 유저가 존재하지 않는 경우
    	case 0:
    		result.data = "unpresent user";
    		result.status = false;
    		break;
    	// 회원탈퇴 완료
    	case 1:
    		result.data = "success";
    		result.status = true;
    		status = HttpStatus.OK;
    		break;
    	}
    	
    	return new ResponseEntity<>(result, status);
    }
    
    @Autowired
    UserEmailFindService userEmailFindService;
    
    @PostMapping("/find-email")
    @ApiOperation("이메일찾기")
    public Object findEmail(@Valid @RequestBody UserEmailFindRequest request) {
    	final BasicResponse result = new BasicResponse();
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	    	
    	String findEmailResult = userEmailFindService.userEmailFind(request.getUserNickname(), request.getUserBirthday());
    	
    	if(!findEmailResult.equals("fail")) {
    		result.data = "success";
    		result.status = true;
    		result.object = findEmailResult;
    		status = HttpStatus.OK;
    	}else {
    		result.data = "존재하지 않는 사용자 입니다.";
    		result.status = false;
    	}
    	
    	return new ResponseEntity<>(result, status);
    }
    
    @Autowired
    UserProfileModifyService userProfileModifyService;
    
    @Autowired
	S3Uploader s3Uploader;
    
    @PutMapping("/edit")
    @ApiOperation("프로필수정")
    public Object edit(@RequestParam("nickname") String nickname, @RequestParam("comment") String comment, @RequestParam("image") MultipartFile multipartFile) {
    	final BasicResponse result = new BasicResponse();
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	String imageUrl = null;
    	// S3에 사진전송하고 url 받아오기
    	try {
    		imageUrl = s3Uploader.upload(multipartFile, "profile");
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	User user = new User();
    	user.setUserNickname(nickname);
    	user.setUserImage(imageUrl);
    	user.setUserComment(comment);
    	int editResult = userProfileModifyService.userProfileModify(user);
    	
    	switch(editResult) {
    	// 프로필 수정 요청한 유저가 존재하지 않는 경우
    	case 0:
    		result.data = "unpresent user";
    		result.status = false;
    		break;
    	// 프로필 수정 성공
    	case 1:
    		result.data = "success";
    		result.status = true;
    		result.object = user;
    		status = HttpStatus.OK;
    		break;
    	}
    	
    	return new ResponseEntity<>(result, status);
    }
    
    @Autowired
    UserProfileService userProfileService;
    
    @GetMapping("/profile/{userNickname}")
    @ApiOperation(value = "프로필보기")
    public Object showProfile(@PathVariable("userNickname") String userNickname) {
    	final BasicResponse result = new BasicResponse();
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	
    	if(!userProfileService.userProfileShow(userNickname).equals("fail")) { // 프로필 보기를 요청한 유저의 정보를 정상적으로 받아온 경우
    		result.data = "success";
    		result.status = true;
    		result.object = userProfileService.userProfileShow(userNickname);
    		status = HttpStatus.OK;
    	}else { // 프로필 보기를 요청한 유저의 정보를 찾을 수 없는 경우
    		result.data = "nonpresent user";
    		result.status = false;
    	}
    	
    	return new ResponseEntity<>(result, status);
    	
    }
    
    @Autowired
    UserPasswordFindService userPasswordFindService;
    
    @PostMapping("/find-pw")
    @ApiOperation("임시비밀번호전송")
    public Object sendEmail(@Valid @RequestBody UserEmailRequest request) {
    	final BasicResponse result = new BasicResponse();
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	
    	int findPasswordResult = userPasswordFindService.userPasswordFind(request.getUserEmail(), request.getUserNickname(), request.getUserBirthday());
    	
    	switch(findPasswordResult) {
    	// 해당하는 유저가 없는 경우
    	case 0:
    		result.data = "unpresent user";
    		result.status = false;
    		break;
    	// 임시 비밀번호 전송 성공
    	case 1:
    		result.data = "success";
    		result.status = true;
    		break;
    	}
    	
    	return new ResponseEntity<>(result, status);
    }
}