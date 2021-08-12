package com.course2go.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);
	
	@PostMapping("/like/{bid}")
    @ApiOperation(value = "좋아요")
	public Object likeBoard(@PathVariable Integer bid, @RequestHeader Map<String, Object> header) {
		logger.info("보드 좋아요 시작 : bid = " +bid);
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

	@DeleteMapping("/like/{bid}")
    @ApiOperation(value = "좋아요취소")
	public Object cancelLikeBoard(@PathVariable Integer bid, @RequestHeader Map<String, Object> header) {
		logger.info("보드 좋아요 취소 시작 : bid = " +bid);
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

	@PostMapping("/scrap/{bid}")
    @ApiOperation(value = "스크랩")
	public Object scrapBoard(@PathVariable Integer bid, @RequestHeader Map<String, Object> header) {
		logger.info("보드 스크랩 시작 : bid = " +bid);
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

	@DeleteMapping("/scrap/{bid}")
    @ApiOperation(value = "스크랩취소")
	public Object cancelScrapeBoard(@PathVariable Integer bid, @RequestHeader Map<String, Object> header) {
		logger.info("보드 스크랩 취소 시작 : bid = " +bid);
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
