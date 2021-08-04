package com.course2go.service.scrap;

public interface ScrapService {
	public boolean scrap(String uid, Integer bid);
	public boolean cancelScrap(String uid, Integer bid);
	
}
