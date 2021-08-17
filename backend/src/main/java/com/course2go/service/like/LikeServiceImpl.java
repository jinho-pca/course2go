package com.course2go.service.like;

import java.util.List;
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
		List<Like> likelist = likeDao.findAllByLikeUidAndLikeBid(uid, bid);
		boolean exist = !likelist.isEmpty();
		if (exist) {
			likeDao.deleteById(likelist.get(0).getId());
		}
		return exist;
	}

	@Override
	public boolean iLike(String uid, Integer bid) {
		List<Like> likeOpt = likeDao.findAllByLikeUidAndLikeBid(uid, bid);
		return !likeOpt.isEmpty();
	}
}
