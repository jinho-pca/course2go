package com.course2go.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course2go.model.BasicResponse;
import com.course2go.model.place.PlaceDto;
import com.course2go.model.route.RouteWriteRequest;
import com.course2go.service.place.PlaceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@Api(value="search")
@RequestMapping("/search")
public class SearchController {

	@Autowired
	PlaceService placeService;
	
	@GetMapping("/place/{word}")
    @ApiOperation(value = "장소검색")
    public List<PlaceDto> searchPlace(@PathVariable String word) {
		return placeService.searchPlace(word);
    }
}
