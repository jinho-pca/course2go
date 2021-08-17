package com.course2go.service.like;

public interface LikeService {
	public boolean like(String uid, Integer bid);
	public boolean cancelLike(String uid, Integer bid);
	public boolean iLike(String uid, Integer bid);
}
