package com.course2go.controller;

import java.io.IOException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course2go.authentication.TokenUtils;
import com.course2go.model.BasicResponse;
import com.course2go.model.visit.VisitReadResponse;
import com.course2go.model.visit.VisitWriteDto;
import com.course2go.model.visit.VisitWriteRequest;
import com.course2go.service.image.S3Uploader;
import com.course2go.service.visit.VisitService;

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

    @PostMapping("/write")
    @ApiOperation(value = "방문글작성")
    public Object writeRoute(@Valid @RequestBody VisitWriteRequest request, @RequestHeader Map<String, Object> header) {
		final BasicResponse result = new BasicResponse();
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		
    	String imageUrl1 = null;
    	String imageUrl2 = null;
    	String imageUrl3 = null;
    	boolean imageexist = true;
    	try {
    		imageUrl1 = s3Uploader.upload(request.getVisitImage1(), "visitimage");
		} catch (IOException e) {
			imageexist = false;
			e.printStackTrace();
		}
    	if (imageexist) {
    		try {
        		imageUrl2 = s3Uploader.upload(request.getVisitImage2(), "visitimage");
    		} catch (IOException e) {
    			imageexist = false;
    			e.printStackTrace();
    		}
		}
    	if (imageexist) {
    		try {
        		imageUrl3 = s3Uploader.upload(request.getVisitImage3(), "visitimage");
    		} catch (IOException e) {
    			imageexist = false;
    			e.printStackTrace();
    		}
		}
    	
    	VisitWriteDto dto = new VisitWriteDto(request.getVisitPid(), request.getVisitContent(), request.getVisitTime(), request.getVisitCost(), imageUrl1, imageUrl2, imageUrl3);
    	visitService.writeVisit(uid, dto);
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
    
}
