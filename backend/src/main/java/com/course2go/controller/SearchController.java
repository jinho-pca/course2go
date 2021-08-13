package com.course2go.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.course2go.authentication.TokenUtils;
import com.course2go.model.user.UserDto;
import com.course2go.model.visit.VisitReadResponse;
import com.course2go.service.user.UserService;
import com.course2go.service.visit.VisitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.course2go.model.BasicResponse;
import com.course2go.model.place.PidList;
import com.course2go.model.place.PlaceDto;
import com.course2go.model.route.RouteReadResponse;
import com.course2go.service.place.PlaceService;
import com.course2go.service.route.RouteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@Api(value="search")
@RequestMapping("/search")
public class SearchController {

	private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);
	@Autowired
	PlaceService placeService;
	
	@GetMapping("/place/{word}/{page}")
    @ApiOperation(value = "장소검색")
    public List<PlaceDto> searchPlace(@PathVariable String word, @PathVariable Integer page) {
		logger.info("장소검색 시작 : word = " +word+ " , page = " +page);
		return placeService.searchPlace(word, page);
    }

    @Autowired
	UserService userService;

	@GetMapping("/user/{keyword}")
	@ApiOperation(value = "유저검색")
	public List<UserDto> searchUser(@PathVariable String keyword, @RequestHeader Map<String, Object> requestHeader){
		logger.info("유저검색 시작: keyword = "+keyword);
		final String token = (String) requestHeader.get("authorization");
		Claims claims = TokenUtils.getClaimsFromToken(token);
		String requestNickname = (String) claims.get("userNickname");
		return userService.searchUser(requestNickname, keyword);
	}

	@Autowired
	VisitService visitService;
	
	@GetMapping("/visit/{pid}")
	@ApiOperation(value = "장소로 방문게시글 검색")
	public Object searchVisit(@PathVariable Integer pid){
		logger.info("장소로 방문게시글 검색 시작 : pid = " +pid);
		final BasicResponse result = new BasicResponse();
		List<VisitReadResponse> response = visitService.getVisitListByPid(pid);
        result.status = true;
        result.data = "success";
        result.object = response;
		logger.info("장소로 방문게시글 검색 종료");
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@Autowired
	RouteService routeService;
    
	@GetMapping("/route")
	@ApiOperation(value = "장소로 동선게시글 검색")
	public Object searchRoute(@RequestParam Integer pid1, @RequestParam Integer pid2, @RequestParam Integer pid3, @RequestParam Integer pid4, @RequestParam Integer pid5, @RequestParam Integer pid6, @RequestParam Integer pid7, @RequestParam Integer pid8, @RequestParam Integer pid9) throws JsonMappingException, JsonProcessingException{
		final BasicResponse result = new BasicResponse();
		logger.info("장소로 동선게시글 검색 시작");
		List<Integer> pids = new LinkedList<Integer>();
		if (pid1!=-1) pids.add(pid1);
		if (pid2!=-1) pids.add(pid2);
		if (pid3!=-1) pids.add(pid3);
		if (pid4!=-1) pids.add(pid4);
		if (pid5!=-1) pids.add(pid5);
		if (pid6!=-1) pids.add(pid6);
		if (pid7!=-1) pids.add(pid7);
		if (pid8!=-1) pids.add(pid8);
		if (pid9!=-1) pids.add(pid9);
		logger.info("- pid리스트 추출");
		logger.info(pids.toString());
		List<RouteReadResponse> response = routeService.getRouteContainPids(pids);
        result.status = true;
        result.data = "success";
        result.object = response;
		logger.info("장소로 동선게시글 검색 종료");
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
