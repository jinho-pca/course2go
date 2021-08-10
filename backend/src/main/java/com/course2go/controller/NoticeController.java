package com.course2go.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course2go.authentication.TokenUtils;
import com.course2go.model.BasicResponse;
import com.course2go.model.notice.Notice;
import com.course2go.model.notice.NoticeDto;
import com.course2go.service.notice.NoticeBoardService;
import com.course2go.service.notice.NoticeFollowService;

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
	NoticeFollowService noticeFollowService;
	@Autowired
	NoticeBoardService noticeBoardService;

	@GetMapping("/follow/new")
    @ApiOperation(value = "새 팔로우 관련 알림")
	public Object newFollow(@RequestHeader Map<String, Object> header) {
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		List<NoticeDto> noticeList = noticeFollowService.noticeNewFollow(uid);
		System.out.println(noticeList);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = noticeList;
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping("/follow/old")
    @ApiOperation(value = "팔로우 관련 알림")
	public Object oldFollow(@RequestHeader Map<String, Object> header) {
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		List<NoticeDto> noticeList = noticeFollowService.noticeOldFollow(uid);
		System.out.println(noticeList);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = noticeList;
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping("/board/new")
    @ApiOperation(value = "새 보드 관련 알림")
	public Object newBoard(@RequestHeader Map<String, Object> header) {
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		List<NoticeDto> noticeList = noticeBoardService.noticeNewBoard(uid);
		System.out.println(noticeList);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = noticeList;
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping("/board/old")
    @ApiOperation(value = "보드 관련 알림")
	public Object oldBoard(@RequestHeader Map<String, Object> header) {
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		List<NoticeDto> noticeList = noticeBoardService.noticeOldBoard(uid);
		System.out.println(noticeList);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = noticeList;
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PostMapping("/follow/update")
    @ApiOperation(value = "보드 관련 알림 확인 후 신규알림 기존알림으로 변경")
	public Object updateFollow(@RequestHeader Map<String, Object> header) {
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		noticeFollowService.update(uid);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PostMapping("/board/update")
    @ApiOperation(value = "보드 관련 알림 확인 후 신규알림 기존알림으로 변경")
	public Object updateBoard(@RequestHeader Map<String, Object> header) {
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		noticeBoardService.update(uid);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
//	@GetMapping("/followcall/new")
//    @ApiOperation(value = "새 팔로우 요청")
//	public Object newfollowcall(@RequestHeader Map<String, Object> header) {
//		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
//		List<Notice> noticeList = noticeFollowService.noticeNewFollowcall(uid);
//		final BasicResponse result = new BasicResponse();
//        result.status = true;
//        result.data = "success";
//        result.object = noticeList;
//		return new ResponseEntity<>(result,HttpStatus.OK);
//	}
//	
//	@GetMapping("/followresult/new")
//    @ApiOperation(value = "새 팔로우 결과")
//	public Object newfollowresult(@RequestHeader Map<String, Object> header) {
//		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
//		List<Notice> noticeList = noticeFollowService.noticeNewFollowresult(uid);
//		final BasicResponse result = new BasicResponse();
//        result.status = true;
//        result.data = "success";
//        result.object = noticeList;
//		return new ResponseEntity<>(result,HttpStatus.OK);
//	}
//	
//	@GetMapping("/scrap/new")
//    @ApiOperation(value = "새 스크랩")
//	public Object newscrap(@RequestHeader Map<String, Object> header) {
//		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
//		List<Notice> noticeList = noticeBoardService.noticeNewScrap(uid);
//		final BasicResponse result = new BasicResponse();
//        result.status = true;
//        result.data = "success";
//        result.object = noticeList;
//		return new ResponseEntity<>(result,HttpStatus.OK);
//	}
//	
//	@GetMapping("/comment/new")
//    @ApiOperation(value = "새 댓글")
//	public Object newcomment(@RequestHeader Map<String, Object> header) {
//		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
//		List<Notice> noticeList = noticeBoardService.noticeNewComment(uid);
//		final BasicResponse result = new BasicResponse();
//        result.status = true;
//        result.data = "success";
//        result.object = noticeList;
//		return new ResponseEntity<>(result,HttpStatus.OK);
//	}
//	
//	@GetMapping("/followcall/old")
//    @ApiOperation(value = "팔로우 요청")
//	public Object oldfollowcall(@RequestHeader Map<String, Object> header) {
//		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
//		List<Notice> noticeList = noticeFollowService.noticeOldFollowcall(uid);
//		final BasicResponse result = new BasicResponse();
//        result.status = true;
//        result.data = "success";
//        result.object = noticeList;
//		return new ResponseEntity<>(result,HttpStatus.OK);
//	}
//	
//	@GetMapping("/followresult/old")
//    @ApiOperation(value = "팔로우 결과")
//	public Object oldfollowresult(@RequestHeader Map<String, Object> header) {
//		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
//		List<Notice> noticeList = noticeFollowService.noticeOldFollowresult(uid);
//		final BasicResponse result = new BasicResponse();
//        result.status = true;
//        result.data = "success";
//        result.object = noticeList;
//		return new ResponseEntity<>(result,HttpStatus.OK);
//	}
//	
//	@GetMapping("/scrap/old")
//    @ApiOperation(value = "스크랩")
//	public Object oldscrap(@RequestHeader Map<String, Object> header) {
//		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
//		List<Notice> noticeList = noticeBoardService.noticeOldScrap(uid);
//		final BasicResponse result = new BasicResponse();
//        result.status = true;
//        result.data = "success";
//        result.object = noticeList;
//		return new ResponseEntity<>(result,HttpStatus.OK);
//	}
//	
//	@GetMapping("/comment/old")
//    @ApiOperation(value = "댓글")
//	public Object oldcomment(@RequestHeader Map<String, Object> header) {
//		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
//		List<Notice> noticeList = noticeBoardService.noticeOldComment(uid);
//		final BasicResponse result = new BasicResponse();
//        result.status = true;
//        result.data = "success";
//        result.object = noticeList;
//		return new ResponseEntity<>(result,HttpStatus.OK);
//	}
}