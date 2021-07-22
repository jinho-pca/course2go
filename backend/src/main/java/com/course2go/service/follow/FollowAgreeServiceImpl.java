package com.course2go.service.follow;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.course2go.dao.FollowDao;
import com.course2go.model.follow.Follow;
import com.course2go.model.notice.Notice;

@Service
public class FollowAgreeServiceImpl implements FollowAgreeService {

	@Autowired
    FollowDao followDao;
	
//  @Autowired
//  NoticeDao noticeDao;
	
	@Override
	@Transactional
	public boolean agree(Notice notice) {
		
    	//JWT 토큰을 이용하여 받아와야 함. 
    	String followToUid = notice.getNoticeUid();
    	String followFromUid = notice.getNoticeFromUid();
    	
    	// 아래 두 기능을 Transaction하게 관리해야함.
    	
    	// Notice 삭제
//    	noticeDao.deleteByNid(notice.getNid());
    	
    	// Follow 추가
    	followDao.save(Follow.builder(followFromUid, followToUid).build());
		return true;
		
		
		
	}
	
	

	
}
