package com.course2go.service.notice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.NoticeDao;
import com.course2go.model.notice.Notice;
import com.course2go.model.notice.NoticeDto;

@Service
public class NoticeFollowServiceImpl implements NoticeFollowService {

	@Autowired
	NoticeDao noticeDao;
	ModelMapper modelmapper;

	private final boolean isnew=true;
	private final int followcall=1;
	private final int followresult=2;
	private final int scrap=3;
	private final int comment=4;
	private final ArrayList<Integer> follow = new ArrayList<Integer>();
	private final ArrayList<Integer> board = new ArrayList<Integer>();
	public NoticeFollowServiceImpl() {
		modelmapper = new ModelMapper();
		follow.add(1);
		follow.add(2);
		board.add(3);
		board.add(4);
	}
	
	
	@Override
	public List<NoticeDto> noticeNewFollow(String noticeUid) {
		List<Notice> newFollowList = noticeDao.findAllByNoticeIsnewAndNoticeTypeInAndNoticeUid(isnew, follow, noticeUid);
		List<NoticeDto> newFollowListDto = newFollowList.stream().map(entityList -> modelmapper.map(entityList, NoticeDto.class)).collect(Collectors.toList());
		return newFollowListDto;
	}
	@Override
	public List<NoticeDto> noticeOldFollow(String noticeUid) {
		List<Notice> newBoardList = noticeDao.findAllByNoticeIsnewAndNoticeTypeInAndNoticeUid(isnew, board, noticeUid);
		List<NoticeDto> newBoardListDto = newBoardList.stream().map(entityList -> modelmapper.map(entityList, NoticeDto.class)).collect(Collectors.toList());
		return newBoardListDto;
	}
	
	@Override
	public List<Notice> noticeNewFollowcall(String noticeUid){		
		return noticeDao.findAllByNoticeIsnewAndNoticeTypeAndNoticeUid(isnew, followcall, noticeUid);
	}
	@Override
	public List<Notice> noticeOldFollowcall(String noticeUid){		
		return noticeDao.findAllByNoticeIsnewAndNoticeTypeAndNoticeUid(!isnew, followcall, noticeUid);
	}
	@Override
	public List<Notice> noticeNewFollowresult(String noticeUid){		
		return noticeDao.findAllByNoticeIsnewAndNoticeTypeAndNoticeUid(isnew, followresult, noticeUid);
	}
	@Override
	public List<Notice> noticeOldFollowresult(String noticeUid){		
		return noticeDao.findAllByNoticeIsnewAndNoticeTypeAndNoticeUid(!isnew, followresult, noticeUid);
	}

}
