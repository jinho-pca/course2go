package com.course2go.service.follow;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.course2go.dao.FollowDao;
import com.course2go.model.follow.Follow;
import com.course2go.model.notice.Notice;
import com.course2go.model.notice.NoticeDto;
import com.course2go.service.notice.NoticeService;
import com.course2go.service.user.UserService;

@Service
public class FollowManagementServiceImpl implements FollowManagementService {

	@Autowired
    FollowDao followDao;
	
	@Autowired
	NoticeService noticeService;
	
	@Autowired
	UserService userService;
	
	@Override
	@Transactional
	public void agree(Notice notice) {
		
    	String followToUid = notice.getNoticeUid();
    	String followFromUid = notice.getNoticeFromUid();
    	
    	// 아래 두 기능을 Transaction하게 관리해야함.

		// Notice 삭제
		noticeService.deleteNotice(notice);
		
		// 완료 Notice 생성
		noticeService.writeNotice(followToUid, 2, followFromUid, true);
		
		// Follow 추가
		followDao.save(Follow.builder(followFromUid, followToUid).build());

	}

	@Override
	public void deny(Notice notice) {
		noticeService.deleteNotice(notice);	
	}

	@Override
	public void unfollow(String followFromNickname, String followToNickname) {
		
		String followFromUid = userService.getUidByUserNickname(followFromNickname);
		String followToUid = userService.getUidByUserNickname(followToNickname);
		
		Optional<Follow> toUnfollow = followDao.getFollowByFollowFromUidAndFollowToUid(followFromUid, followToUid);
		
		if(toUnfollow.isPresent()) {
			followDao.delete(toUnfollow.get());
		}

	}

	@Override
	public void follow(String followFromNickname, String followToNickname) {
		
		String followFromUid = userService.getUidByUserNickname(followFromNickname);
		String followToUid = userService.getUidByUserNickname(followToNickname);
		
		noticeService.writeNotice(followToUid, 1, followFromUid, true);
	}

	
	
	
	
	

	
}
