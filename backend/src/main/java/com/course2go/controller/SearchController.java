package com.course2go.controller;

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
		return placeService.searchPlace(word, page);
    }

    @Autowired
	UserService userService;

	@GetMapping("/user/{keyword}")
	@ApiOperation(value = "유저검색")
	public List<UserDto> searchUser(@PathVariable String keyword, @RequestHeader Map<String, Object> requestHeader){

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
		final BasicResponse result = new BasicResponse();
		List<VisitReadResponse> response = visitService.getVisitListByPid(pid);
        result.status = true;
        result.data = "success";
        result.object = response;
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@Autowired
	RouteService routeService;

    @Autowired
    private ObjectMapper mapper;
    
	@GetMapping("/route")
	@ApiOperation(value = "장소로 동선게시글 검색")
	public Object searchRoute(@RequestParam String pidList) throws JsonMappingException, JsonProcessingException{
		final BasicResponse result = new BasicResponse();
		System.out.println("검색과정 - 도착");
		PidList pidLists = mapper.readValue(pidList, PidList.class);
		System.out.println("검색과정 - mapper 작업");
		System.out.println(pidLists.toString());
		List<Integer> pids = pidLists.getPids();
		System.out.println("검색과정 - pid리스트 추출");
		System.out.println(pids.toString());
		
		List<RouteReadResponse> response = routeService.getRouteContainPids(pids);
        result.status = true;
        result.data = "success";
        result.object = response;
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
