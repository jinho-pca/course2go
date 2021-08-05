package com.course2go.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course2go.authentication.AuthConstants;
import com.course2go.authentication.TokenUtils;
import com.course2go.dao.FollowDao;
import com.course2go.exception.UserUnmatchedException;
import com.course2go.model.BasicResponse;
import com.course2go.model.follow.Follow;
import com.course2go.model.notice.Notice;
import com.course2go.model.user.User;
import com.course2go.service.follow.FollowManagementService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;

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
	
	private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);
	
	@GetMapping("/follow/follower")
	public Object getFollowerList(@RequestParam(required = true) final String nickname) {
		ResponseEntity<BasicResponse> response = null;
		List<String> userList = null;
		userList = followListService.getFollowerListByNickname(nickname);
		
		BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		result.object = userList;
		response = new ResponseEntity<>(result, HttpStatus.OK);
		
		return response;
	}
	
	
	
	@GetMapping("/follow/following")
	public Object getFollowingList(@RequestParam(required = true) final String nickname) {
		ResponseEntity<BasicResponse> response = null;
		List<String> userList = null;
		userList = followListService.getFollowingListByNickname(nickname);
		
		BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		result.object = userList;
		response = new ResponseEntity<>(result, HttpStatus.OK);
		
		return response;
	}

	@GetMapping("/follow/myfollower")
	public Object getMyFollowerList(@RequestHeader Map<String, Object> header) {
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		ResponseEntity<BasicResponse> response = null;
		List<String> userList = null;
		userList = followListService.getFollowerListByUid(uid);
		
		BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		result.object = userList;
		response = new ResponseEntity<>(result, HttpStatus.OK);
		
		return response;
	}
	
	@GetMapping("/follow/myfollowing")
	public Object getMyFollowingList(@RequestHeader Map<String, Object> header) {
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		ResponseEntity<BasicResponse> response = null;
		List<String> userList = null;
		userList = followListService.getFollowingListByUid(uid);
		
		BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		result.object = userList;
		response = new ResponseEntity<>(result, HttpStatus.OK);
		
		return response;
	}
	
    @PostMapping("/follow")
    public Object agreeFollow(@RequestBody Notice notice, @RequestHeader Map<String, Object> header) {
    	
    	if(!TokenUtils.isSameUid((String)header.get("authorization"), notice.getNoticeUid())) {
    		throw new UserUnmatchedException(notice.getNoticeUid());
    	}
    	
    	ResponseEntity<BasicResponse> response = null;
    	
    	followManagementService.agree(notice);
		BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
    	
    }
    
    @DeleteMapping("/follow")
    public Object denyFollow(@RequestBody Notice notice, @RequestHeader Map<String, Object> header) {
    	
    	if(!TokenUtils.isSameUid((String)header.get("authorization"), notice.getNoticeUid())) {
    		throw new UserUnmatchedException(notice.getNoticeUid());
    	}
    	
    	ResponseEntity<BasicResponse> response = null;
    	
    	followManagementService.deny(notice);
		BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
    	
    }

    @DeleteMapping("/follow/unfollow")
    public Object unfollow(@RequestParam String followFromNickname, @RequestParam String followToNickname, @RequestHeader Map<String, Object> header) {
    	
    	if(!TokenUtils.isSameNickname((String)header.get("authorization"), followFromNickname)) {
    		throw new UserUnmatchedException(followFromNickname);
    	}
    	
    	ResponseEntity<BasicResponse> response = null;
    	BasicResponse result = new BasicResponse();
    	followManagementService.unfollow(followFromNickname, followToNickname);
    	result.status = true;
		result.data = "success";
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
    }
}