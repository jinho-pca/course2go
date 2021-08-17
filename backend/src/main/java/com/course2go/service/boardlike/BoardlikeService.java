package com.course2go.service.boardlike;

public interface BoardlikeService {
	public boolean like(String uid, Integer bid);
	public boolean cancelLike(String uid, Integer bid);
	public boolean iLike(String uid, Integer bid);
}
