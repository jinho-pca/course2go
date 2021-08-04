package com.course2go.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.course2go.authentication.TokenUtils;
import com.course2go.model.BasicResponse;
import com.course2go.model.board.BoardMyList;
import com.course2go.model.visit.VisitReadResponse;
import com.course2go.model.visit.VisitWriteDto;
import com.course2go.model.visit.VisitWriteRequest;
import com.course2go.service.image.S3Uploader;
import com.course2go.service.visit.VisitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@Api(value="visit")
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    VisitService visitService;

    @Autowired
	S3Uploader s3Uploader;

    @Autowired
    private ObjectMapper mapper;
    
    @PostMapping("/write")
    @ApiOperation(value = "방문글작성")
    public Object writeRoute(@RequestParam(value = "rid") Integer rid, @RequestParam(value = "request") String req, @RequestHeader Map<String, Object> header, @RequestParam(required = false, value = "image1")MultipartFile image1, @RequestParam(required = false, value = "image2")MultipartFile image2, @RequestParam(required = false, value = "image3")MultipartFile image3) throws JsonMappingException, JsonProcessingException {
		final BasicResponse result = new BasicResponse();
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		
    	String imageUrl1 = null;
    	String imageUrl2 = null;
    	String imageUrl3 = null;
    	if (!image1.isEmpty()) {
	    	try {
	    		imageUrl1 = s3Uploader.upload(image1, "visitimage");
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	if (!image2.isEmpty()) {
    		try {
        		imageUrl2 = s3Uploader.upload(image2, "visitimage");
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
		}
    	if (!image3.isEmpty()) {
    		try {
        		imageUrl3 = s3Uploader.upload(image3, "visitimage");
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
		}
    	System.out.println(rid);
    	VisitWriteRequest request = mapper.readValue(req, VisitWriteRequest.class);
    	
    	VisitWriteDto dto = new VisitWriteDto(request.getVisitPid(), request.getVisitContent(), request.getVisitTime(), request.getVisitCost(), imageUrl1, imageUrl2, imageUrl3);
    	if (rid==null) {
			System.out.println("동선이 없는 게시글");
    		visitService.writeVisit(uid, request.getTitle(), dto);
		} else {
			System.out.println("동선이 있는 게시글");
    		visitService.writeVisit(uid, request.getTitle(), dto, rid);
		}
        result.status = true;
        result.data = "success";
		return result;
    }
    
    @PostMapping("/read")
    @ApiOperation(value = "게시글읽기")
    public Object readVisit(@RequestParam Integer bid) {
    	VisitReadResponse response = visitService.readVisitBoard(bid);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = response;
		return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/mylist")
    @ApiOperation("내가 쓴 장소글 목록")
    public Object getListRoute(@RequestHeader Map<String, Object> header) {
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		List<VisitReadResponse> response = visitService.getMyVisitList(uid);
    	final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = response;
    	return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
