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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course2go.authentication.TokenUtils;
import com.course2go.model.BasicResponse;
import com.course2go.model.comment.CommentDto;
import com.course2go.model.comment.CommentWriteRequest;
import com.course2go.service.comment.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@Api(value="comment")
@RequestMapping("/comment")
public class CommentController {
	
	private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);
	@Autowired
	CommentService commentService;
	
	@GetMapping("/read/{bid}")
    @ApiOperation(value = "댓글 목록")
	public Object readComment(@PathVariable Integer bid) {
		logger.info("댓글목록호출 시작");
		List<CommentDto> commentList = commentService.readSortedComment(bid);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = commentList;
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	@PostMapping("/write")
    @ApiOperation(value = "댓글 쓰기")
	public Object writeComment(@Valid @RequestBody CommentWriteRequest request, @RequestHeader Map<String, Object> header) {
		logger.info("댓글쓰기 시작");
		String uid = TokenUtils.getUidFromToken((String)header.get("authorization"));
		commentService.writeComment(uid, request);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
		logger.info("댓글쓰기 완료");
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	@PostMapping("/delete")
    @ApiOperation(value = "댓글삭제")
	public Object deleteComment(@Valid @RequestParam Integer cid, @RequestHeader Map<String, Object> header) {
		logger.info("댓글삭제 시작");
		commentService.deleteComment(cid);;
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
		logger.info("댓글삭제 완료");
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
