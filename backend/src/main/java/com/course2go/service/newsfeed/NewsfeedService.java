package com.course2go.service.newsfeed;

import java.util.List;

import com.course2go.model.newsfeed.NewsfeedDto;

public interface NewsfeedService {
	
	public List<NewsfeedDto> getListNewsfeed(String uid);

}
