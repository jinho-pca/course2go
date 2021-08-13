package com.course2go.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);
	
	@GetMapping("/read")
    @ApiOperation(value = "뉴스피드 목록")
	public Object readNewsfeed(@RequestHeader Map<String, Object> header) {
		logger.info("뉴스피드 목록 호출 시작");
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		List<NewsfeedDto> newsfeedList = newsfeedService.getListNewsfeed(uid);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = newsfeedList;
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
