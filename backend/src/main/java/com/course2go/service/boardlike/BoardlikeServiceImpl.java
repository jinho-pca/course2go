package com.course2go.service.boardlike;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.BoardDao;
import com.course2go.dao.BoardlikeDao;
import com.course2go.model.board.Board;
import com.course2go.model.boardlike.Boardlike;
import com.course2go.service.board.BoardService;

@Service
public class BoardlikeServiceImpl implements BoardlikeService {
	
	@Autowired
	BoardlikeDao likeDao;
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	@Transactional
	public boolean like(String uid, Integer bid) {
		boolean exist = likeDao.existsByLikeUidAndLikeBid(uid, bid);
		if (!exist) {
			likeDao.save(Boardlike.builder(uid, bid).build());
		}
		
		Board board = boardDao.findAllByBid(bid);
		board.addLike();
		boardDao.save(board);
		return !exist;
	}

	@Override
	public boolean cancelLike(String uid, Integer bid) {
		List<Boardlike> likelist = likeDao.findAllByLikeUidAndLikeBid(uid, bid);
		boolean exist = !likelist.isEmpty();
		if (exist) {
			likeDao.deleteById(likelist.get(0).getId());
		}
		
		Board board = boardDao.findAllByBid(bid);
		board.minusLike();
		boardDao.save(board);
		
		return exist;
	}

	@Override
	public boolean iLike(String uid, Integer bid) {
		List<Boardlike> likeOpt = likeDao.findAllByLikeUidAndLikeBid(uid, bid);
		return !likeOpt.isEmpty();
	}

	@Override
	public void deleteBoardlike(Integer bid) {
		likeDao.deleteAllByLikeBid(bid);
	}
}
