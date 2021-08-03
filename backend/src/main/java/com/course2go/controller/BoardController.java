package com.course2go.controller;

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
import com.course2go.service.like.LikeService;
import com.course2go.service.scrap.ScrapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@Api(value="board")
@RequestMapping("/board")
public class BoardController {

	@Autowired
	LikeService likeService;
	
	@Autowired
	ScrapService scrapService;
	
	@GetMapping("/like")
    @ApiOperation(value = "좋아요")
	public Object likeBoard(@RequestParam(required = true) final Integer bid, @RequestHeader Map<String, Object> header) {
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		final BasicResponse result = new BasicResponse();
        result.status = likeService.like(uid, bid);
        if (result.status) {
            result.data = "success";
		} else {
	        result.data = "fail";
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	@GetMapping("/cancellike")
    @ApiOperation(value = "좋아요취소")
	public Object cancelLikeBoard(@RequestParam(required = true) final Integer bid, @RequestHeader Map<String, Object> header) {
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		final BasicResponse result = new BasicResponse();
        result.status = likeService.cancelLike(uid, bid);
        if (result.status) {
            result.data = "success";
		} else {
	        result.data = "fail";
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	@GetMapping("/scrap")
    @ApiOperation(value = "스크랩")
	public Object scrapBoard(@RequestParam(required = true) final Integer bid, @RequestHeader Map<String, Object> header) {
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		final BasicResponse result = new BasicResponse();
        result.status = scrapService.scrap(uid, bid);
        if (result.status) {
            result.data = "success";
		} else {
	        result.data = "fail";
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	@GetMapping("/cancelscrap")
    @ApiOperation(value = "스크랩취소")
	public Object cancelScrapeBoard(@RequestParam(required = true) final Integer bid, @RequestHeader Map<String, Object> header) {
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		final BasicResponse result = new BasicResponse();
        result.status = scrapService.cancelScrap(uid, bid);
        if (result.status) {
            result.data = "success";
		} else {
	        result.data = "fail";
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	
}
