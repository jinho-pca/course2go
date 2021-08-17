package com.course2go.service.like;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.LikeDao;
import com.course2go.model.like.Like;

@Service
public class LikeServiceImpl implements LikeService {
	
	@Autowired
	LikeDao likeDao;

	@Override
	public boolean like(String uid, Integer bid) {
		boolean exist = likeDao.existsByLikeUidAndLikeBid(uid, bid);
		if (!exist) {
			likeDao.save(Like.builder(uid, bid).build());
		}
		return !exist;
	}

	@Override
	public boolean cancelLike(String uid, Integer bid) {
		Optional<Like> likeOpt = likeDao.findLikeByLikeUidAndLikeBid(uid, bid);
		boolean exist = likeOpt.isPresent();
		if (exist) {
			likeDao.deleteById(likeOpt.get().getId());
		}
		return exist;
	}

	@Override
	public boolean iLike(String uid, Integer bid) {
		Optional<Like> likeOpt = likeDao.findLikeByLikeUidAndLikeBid(uid, bid);
		return likeOpt.isPresent();
	}
}
