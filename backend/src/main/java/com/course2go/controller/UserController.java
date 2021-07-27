package com.course2go.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course2go.dao.UserDao;
import com.course2go.model.BasicResponse;
import com.course2go.model.user.SignupRequest;
import com.course2go.model.user.User;
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
        
//        User user = new User();
//        user.setUserName(request.getUserName());
//        user.setUserEmail(request.getUserEmail());
//        user.setUserPassword(request.getUserPassword());
//        user.setUserNickname(request.getUserNickname());
//        user.setUserBirthday(request.getUserBirthday());
//        user.setUserGender(request.getUserGender());
        
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
    
    
    
}