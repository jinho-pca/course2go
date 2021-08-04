package com.course2go.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course2go.authentication.TokenUtils;
import com.course2go.model.BasicResponse;
import com.course2go.model.newsfeed.NewsfeedDto;
import com.course2go.service.newsfeed.NewsfeedService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@Api(value="newsfeed")
@RequestMapping("/newsfeed")
public class NewsfeedController {
	
	@Autowired
	NewsfeedService newsfeedService;
	
	@PostMapping("/read")
    @ApiOperation(value = "뉴스피드 목록")
	public Object readNewsfeed(@RequestHeader Map<String, Object> header) {
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		List<NewsfeedDto> newsfeedList = newsfeedService.getListNewsfeed(uid);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = newsfeedList;
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
