package com.course2go.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course2go.dao.FollowDao;
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
		ResponseEntity response = null;
		List<User> userList = null;
		try {
			userList = followListService.getFollowerList(email);
			// 성공시 response 설정 필요
		} catch (Exception e) {
			// 실패시 응답 필요. 
		}
		
		return response;
	}
	
	@GetMapping("/follow/following")
	public Object getFollowingList(@RequestParam(required = true) final String email) {
		ResponseEntity response = null;
		List<User> userList = null;
		try {
			userList = followListService.getFollowerList(email);
			// 성공시 response 설정 필요
		} catch (Exception e) {
			// 실패시 응답 필요. 
		}
		
		return response;
	}
	
    @PostMapping("/follow")
    public Object agreeFollow(@RequestBody Notice notice) {
    	ResponseEntity response = null;
    	
    	try {
    		followManagementService.agree(notice);
    		// 성공 시 response 설정 필요
		} catch (Exception e) {
			// 실패 시 응답 필요.
		}
    	return response;
    }
    
    @DeleteMapping("/follow")
    public Object denyFollow(@RequestBody Notice notice) {
    	ResponseEntity response = null;
    	
    	try {
    		followManagementService.deny(notice);
    		// 성공 시 response 설정 필요
		} catch (Exception e) {
			// 실패 시 응답 필요.
		}
    	return response;
    }
    
}