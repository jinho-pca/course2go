package com.course2go.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course2go.dao.FollowDao;
import com.course2go.model.BasicResponse;
import com.course2go.model.follow.Follow;
import com.course2go.model.notice.Notice;
import com.course2go.model.user.User;
import com.course2go.service.follow.FollowManagementService;
import com.course2go.service.follow.FollowListService;

//@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
//    @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
//    @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
//    @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })


@CrossOrigin(origins = {"http://localhost:3000"})
@RestController     
public class FollowController {
	
	@Autowired
	FollowManagementService followManagementService;
	@Autowired
	FollowListService followListService;
	
	@GetMapping("/follow/follower")
	public Object getFollowerList(@RequestParam(required = true) final String email) {
		ResponseEntity<BasicResponse> response = null;
		List<User> userList = null;
		userList = followListService.getFollowerList(email);
		
		BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		result.object = userList;
		response = new ResponseEntity<>(result, HttpStatus.OK);
		
		return response;
	}
	
	@GetMapping("/follow/following")
	public Object getFollowingList(@RequestParam(required = true) final String email) {
		ResponseEntity<BasicResponse> response = null;
		List<User> userList = null;
		userList = followListService.getFollowingList(email);
		
		BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		result.object = userList;
		response = new ResponseEntity<>(result, HttpStatus.OK);
		
		return response;
	}
	
    @PostMapping("/follow")
    public Object agreeFollow(@RequestBody Notice notice) {
    	
    	/*
    	 * JWT 토큰을 이용하여 실제 자기가 보낸 요청인지 확인해야함
    	*/
    	
    	ResponseEntity<BasicResponse> response = null;
    	
    	if(followManagementService.agree(notice)) {
    		BasicResponse result = new BasicResponse();
    		result.status = true;
    		result.data = "success";
    		response = new ResponseEntity<>(result, HttpStatus.OK);
    		return response;
    	} else {
    		BasicResponse result = new BasicResponse();
    		result.status = true;
    		result.data = "fail";
    		response = new ResponseEntity<>(result, HttpStatus.OK);
    		return response;
    	}
    }
    
    @DeleteMapping("/follow")
    public Object denyFollow(@RequestBody Notice notice) {
    	
    	/*
    	 * JWT 토큰을 이용하여 실제 자기가 보낸 요청인지 확인해야함
    	*/
    	
    	ResponseEntity<BasicResponse> response = null;
    	
    	if(followManagementService.deny(notice)) {
    		BasicResponse result = new BasicResponse();
    		result.status = true;
    		result.data = "success";
    		response = new ResponseEntity<>(result, HttpStatus.OK);
    		return response;
    	} else {
    		BasicResponse result = new BasicResponse();
    		result.status = true;
    		result.data = "fail";
    		response = new ResponseEntity<>(result, HttpStatus.OK);
    		return response;
    	}
    	
    	
    }
    
}