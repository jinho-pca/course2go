package com.course2go.service.follow;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.course2go.dao.FollowDao;
import com.course2go.dao.NoticeDao;
import com.course2go.dao.UserDao;
import com.course2go.model.follow.Follow;
import com.course2go.model.notice.Notice;

@Service
public class FollowManagementServiceImpl implements FollowManagementService {

	@Autowired
    FollowDao followDao;
	
	@Autowired
	NoticeDao noticeDao;
	
	@Autowired
	UserDao userDao;
	
	@Override
	@Transactional
	public boolean agree(Notice notice) {
		

//    	String followToUid = notice.getNoticeUid();
//    	String followFromUid = notice.getNoticeFromUid();
//    	
//    	// 아래 두 기능을 Transaction하게 관리해야함.
//
//		// Notice 삭제
//		noticeDao.delete(notice);
//		
//		// 완료 Notice 생성
//		Notice resultNotice = new Notice();
//		resultNotice.setNoticeFromUid(notice.getNoticeUid());
//		resultNotice.setNoticeUid(notice.getNoticeFromUid());
//		resultNotice.setNoticeIsnew(true);
//		resultNotice.setNoticeType(2);
//		noticeDao.save(resultNotice);
//		
//		
//		// Follow 추가
//		followDao.save(Follow.builder(followFromUid, followToUid).build());
		return true;
			
		
    	
	}

	@Override
	public boolean deny(Notice notice) {
    	// Notice 삭제
		try {
			noticeDao.delete(notice);			
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean unfollow(String followFromNickname, String followToNickname) {
		
		String followFromUid = userDao.getUserByUserNickname(followFromNickname).get().getUid();
		String followToUid = userDao.getUserByUserNickname(followToNickname).get().getUid();
		
		Optional<Follow> toUnfollow = followDao.getFollowByFollowFromUidAndFollowToUid(followFromUid, followToUid);
		
		if(toUnfollow.isPresent()) {
			followDao.delete(toUnfollow.get());
			return true;
		}
		
		return false;
	}
	
	

	
}
