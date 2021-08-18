package com.course2go.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.course2go.authentication.TokenUtils;
import com.course2go.model.BasicResponse;
import com.course2go.model.route.RouteReadResponse;
import com.course2go.model.route.RouteWriteRequest;
import com.course2go.service.image.S3Uploader;
import com.course2go.service.route.RouteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    @Autowired
	S3Uploader s3Uploader;
    @Autowired
    private ObjectMapper mapper;

	private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);
	
    @PostMapping("/write")
    @ApiOperation(value = "동선작성")
    public Object writeRoute(@RequestParam(value = "request") String req, @RequestHeader Map<String, Object> header, @RequestParam(required = false, value = "image")MultipartFile image) throws JsonMappingException, JsonProcessingException {
//    	@Valid @RequestBody RouteWriteRequest request, 
    	logger.info("동선작성 시작");
    	String imageUrl = null;
    	if (image != null) {
    		logger.info("이미지 입력중");
	    	try {
	    		imageUrl = s3Uploader.upload(image, "visitimage");
			} catch (IOException e) {
				e.printStackTrace();
			}
    		logger.info(imageUrl);
    	}
    	RouteWriteRequest request = mapper.readValue(req, RouteWriteRequest.class);
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
    	routeService.writeRoute(uid, request, imageUrl);
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