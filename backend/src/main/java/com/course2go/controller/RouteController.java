package com.course2go.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course2go.authentication.TokenUtils;
import com.course2go.model.BasicResponse;
import com.course2go.model.route.RouteReadResponse;
import com.course2go.model.route.RouteWriteRequest;
import com.course2go.service.route.RouteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
//        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
//        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })
@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@Api(value="route")
@RequestMapping("/route")
public class RouteController {

	@Autowired
	RouteService routeService;

	private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);
	
    @PostMapping("/write")
    @ApiOperation(value = "동선작성")
    public Object writeRoute(@Valid @RequestBody RouteWriteRequest request, @RequestHeader Map<String, Object> header) {
    	logger.info("동선작성 시작");
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
    	routeService.writeRoute(uid, request);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
		return new ResponseEntity<>(result,HttpStatus.OK);
    }
    
    @GetMapping("/read/{bid}")
    @ApiOperation(value = "동선읽기")
    public Object readRoute(@PathVariable Integer bid, @RequestHeader Map<String, Object> header) {
    	logger.info("동선읽기 시작");
    	String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
    	RouteReadResponse response = routeService.readRouteBoard(bid, uid);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = response;
		return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/mylist")
    @ApiOperation("내가 쓴 동선글 목록")
    public Object getMyListRoute(@RequestHeader Map<String, Object> header) {
    	logger.info("내가 쓴 동선글 목록 시작");
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		List<RouteReadResponse> response = routeService.getMyRouteList(uid, uid);
    	final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = response;
    	return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("/list/{userNickname}")
    @ApiOperation("동선글 목록")
    public Object getListRoute(@PathVariable String userNickname, @RequestHeader Map<String, Object> header) {
    	logger.info("동선글 목록 시작");
    	String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		List<RouteReadResponse> response = routeService.getRouteList(userNickname, uid);
    	final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = response;
    	return new ResponseEntity<>(result, HttpStatus.OK);
    }
}