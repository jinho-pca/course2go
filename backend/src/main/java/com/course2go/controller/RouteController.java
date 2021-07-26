package com.course2go.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course2go.model.BasicResponse;
import com.course2go.model.route.Route;
import com.course2go.model.route.RouteWriteRequest;
import com.course2go.service.route.RouteWriteService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
	RouteWriteService routeWriteService;
	
    @PostMapping("/write")
    @ApiOperation(value = "동선작성")
    public Object signup(@RequestParam String uid,@Valid @RequestBody RouteWriteRequest request) {
    	routeWriteService.writeRoute(uid, request);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
		return new ResponseEntity<>(result,HttpStatus.OK);
    }	
}