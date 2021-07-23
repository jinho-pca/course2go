package com.course2go.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course2go.model.BasicResponse;
import com.course2go.model.notice.Notice;
import com.course2go.service.notice.NoticeCommentService;
import com.course2go.service.notice.NoticeFollowcallService;
import com.course2go.service.notice.NoticeFollowresultService;
import com.course2go.service.notice.NoticeScrapService;

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
@Api(value="notice")
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	NoticeFollowcallService noticeFollowcallService;
	@Autowired
	NoticeFollowresultService noticeFollowresultService;
	@Autowired
	NoticeScrapService noticeScrapService;
	@Autowired
	NoticeCommentService noticeCommentService;
		
	@GetMapping("/followcall/new")
    @ApiOperation(value = "새 팔로우 요청")
	public Object newfollowcall(@RequestParam(required = true) final String uid) {
		List<Notice> noticeList = noticeFollowcallService.noticeNewFollowcall(uid);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = noticeList;
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping("/followresult/new")
    @ApiOperation(value = "새 팔로우 결과")
	public Object newfollowresult(@RequestParam(required = true) final String uid) {
		List<Notice> noticeList = noticeFollowresultService.noticeNewFollowresult(uid);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = noticeList;
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping("/scrap/new")
    @ApiOperation(value = "새 스크랩")
	public Object newscrap(@RequestParam(required = true) final String uid) {
		List<Notice> noticeList = noticeScrapService.noticeNewScrap(uid);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = noticeList;
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping("/comment/new")
    @ApiOperation(value = "새 댓글")
	public Object newcomment(@RequestParam(required = true) final String uid) {
		List<Notice> noticeList = noticeCommentService.noticeNewComment(uid);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = noticeList;
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping("/followcall/old")
    @ApiOperation(value = "팔로우 요청")
	public Object oldfollowcall(@RequestParam(required = true) final String uid) {
		List<Notice> noticeList = noticeFollowcallService.noticeOldFollowcall(uid);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = noticeList;
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping("/followresult/old")
    @ApiOperation(value = "팔로우 결과")
	public Object oldfollowresult(@RequestParam(required = true) final String uid) {
		List<Notice> noticeList = noticeFollowresultService.noticeOldFollowresult(uid);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = noticeList;
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping("/scrap/old")
    @ApiOperation(value = "스크랩")
	public Object oldscrap(@RequestParam(required = true) final String uid) {
		List<Notice> noticeList = noticeScrapService.noticeOldScrap(uid);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = noticeList;
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping("/comment/old")
    @ApiOperation(value = "댓글")
	public Object oldcomment(@RequestParam(required = true) final String uid) {
		List<Notice> noticeList = noticeCommentService.noticeOldComment(uid);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = noticeList;
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
}