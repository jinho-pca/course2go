package com.course2go.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	@Autowired
	CommentService commentService;
	
	@PostMapping("/read")
    @ApiOperation(value = "댓글 목록")
	public Object readComment(@RequestParam(required = true) final Integer bid) {
		List<CommentDto> commentList = commentService.readSortedComment(bid);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = commentList;
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	@PostMapping("/write")
    @ApiOperation(value = "댓글 쓰기")
	public Object writeComment(@RequestParam String uid,@Valid @RequestBody CommentWriteRequest request) {
		commentService.writeComment(uid, request);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
