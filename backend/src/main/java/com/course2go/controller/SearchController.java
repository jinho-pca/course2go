package com.course2go.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.course2go.authentication.TokenUtils;
import com.course2go.model.user.UserDto;
import com.course2go.service.user.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.course2go.model.BasicResponse;
import com.course2go.model.place.PlaceDto;
import com.course2go.model.route.RouteWriteRequest;
import com.course2go.service.place.PlaceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@Api(value="search")
@RequestMapping("/search")
public class SearchController {

	@Autowired
	PlaceService placeService;
	
	@GetMapping("/place/{word}")
    @ApiOperation(value = "장소검색")
    public List<PlaceDto> searchPlace(@PathVariable String word) {
		return placeService.searchPlace(word);
    }

    @Autowired
	UserService userService;

	@GetMapping("/user/{userName}")
	@ApiOperation(value = "유저검색")
	public List<UserDto> searchUser(@PathVariable String userName, @RequestHeader Map<String, Object> requestHeader){

		final String token = (String) requestHeader.get("authorization");
		Claims claims = TokenUtils.getClaimsFromToken(token);
		String requestNickname = (String) claims.get("userNickname");
		return userService.searchUser(requestNickname, userName);
	}
}
