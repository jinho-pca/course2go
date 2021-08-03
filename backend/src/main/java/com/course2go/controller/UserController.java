package com.course2go.controller;

import java.io.IOException;
import java.util.Map;

import javax.validation.Valid;

import com.course2go.authentication.TokenUtils;
import com.course2go.model.user.*;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.course2go.model.BasicResponse;
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

        int registerResult = userRegisterService.userRegister(request);
        
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
    public Object modify(@Valid @RequestBody UserModifyRequest request, @RequestHeader Map<String, Object> requestHeader) {
    	final BasicResponse result = new BasicResponse();
    	HttpStatus status = HttpStatus.CONFLICT;

    	final String token = (String) requestHeader.get("authorization");
		Claims claims = TokenUtils.getClaimsFromToken(token);
		String tokenEmail = (String) claims.get("userEmail");
		System.out.println("token.toString() : " + token);
		System.out.println("claims : " + claims);
		System.out.println("tokenEmail : " + tokenEmail);
    	    	
    	int modifyResult = userModifyService.userModify(tokenEmail, request.getUserNickname(), request.getUserPassword());
    	
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
    
    @DeleteMapping("/delete")
    @ApiOperation("회원탈퇴")
    public Object delete(@RequestBody Map<String, String> request, @RequestHeader Map<String, Object> requestHeader) {
    	final BasicResponse result = new BasicResponse();
    	HttpStatus status = HttpStatus.BAD_REQUEST;

    	final String token = (String) requestHeader.get("authorization");
    	Claims claims = TokenUtils.getClaimsFromToken(token);
    	String tokenEmail = (String) claims.get("userEmail");
    	String requestPassword = (String) request.get("requestPassword");
    	int deleteResult = userDeleteService.userDelete(tokenEmail, requestPassword);

    	switch(deleteResult) {
    	// 회원탈퇴 요청한 유저가 존재하지 않는 경우
    	case -1:
    		result.data = "unpresent user";
    		result.status = false;
    		break;
    	// 비밀번호가 일치하지 않는 경우
		case 0:
				result.data = "incorrect password";
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
    public Object edit(@RequestHeader Map<String, Object> requestHeader, @RequestParam("comment") String requestComment, @RequestParam(required = false, value = "image")MultipartFile multipartFile) {
    	final BasicResponse result = new BasicResponse();
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	String imageUrl = null;

    	final String token = (String) requestHeader.get("authorization");
    	Claims claims = TokenUtils.getClaimsFromToken(token);
    	String tokenEmail = (String) claims.get("userEmail");

    	// S3에 사진전송하고 url 받아오기
		if(!multipartFile.isEmpty()){
			try {
				imageUrl = s3Uploader.upload(multipartFile, "profile");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

    	int editResult = userProfileModifyService.userProfileModify(tokenEmail, requestComment, imageUrl);
    	
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
    public Object sendEmail(@Valid @RequestBody UserEmailRequest userEmailRequest, @RequestHeader Map<String, Object> requestHeader) {
    	final BasicResponse result = new BasicResponse();
    	HttpStatus status = HttpStatus.BAD_REQUEST;

    	int findPasswordResult = userPasswordFindService.userPasswordFind(userEmailRequest);
    	
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