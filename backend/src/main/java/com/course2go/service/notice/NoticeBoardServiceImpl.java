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
import com.course2go.service.user.UserService;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService {

	@Autowired
	NoticeDao noticeDao;
	@Autowired
	NoticeService noticeService;
	@Autowired
	UserService userService;
	ModelMapper modelmapper;
	
	private final boolean isnew=true;
	private final int scrap=3;
	private final int comment=4;
	private final ArrayList<Integer> board = new ArrayList<Integer>();
	public NoticeBoardServiceImpl() {
		modelmapper = new ModelMapper();
		board.add(3);
		board.add(4);
	}
	@Override
	public void update(String noticeUid) {
		noticeService.update(noticeUid, board);
	}
	@Override
	public List<NoticeDto> noticeNewBoard(String noticeUid) {
		List<Notice> newBoardList = noticeDao.findAllByNoticeIsnewAndNoticeTypeInAndNoticeUid(isnew, board, noticeUid);
		List<NoticeDto> newBoardListDto = newBoardList.stream().map(entityList -> modelmapper.map(entityList, NoticeDto.class)).collect(Collectors.toList());
		for (NoticeDto noticeDto : newBoardListDto) {
			noticeDto.setNoticeFromUserNickname(userService.getUserNicknameByUid(noticeDto.getNoticeFromUid()));
		}
		return newBoardListDto;
	}
	@Override
	public List<NoticeDto> noticeOldBoard(String noticeUid) {
		List<Notice> newBoardList = noticeDao.findAllByNoticeIsnewAndNoticeTypeInAndNoticeUid(!isnew, board, noticeUid);
		List<NoticeDto> newBoardListDto = newBoardList.stream().map(entityList -> modelmapper.map(entityList, NoticeDto.class)).collect(Collectors.toList());
		for (NoticeDto noticeDto : newBoardListDto) {
			noticeDto.setNoticeFromUserNickname(userService.getUserNicknameByUid(noticeDto.getNoticeFromUid()));
		}
		return newBoardListDto;
	}
	@Override
	public List<Notice> noticeNewScrap(String noticeUid){		
		return noticeDao.findAllByNoticeIsnewAndNoticeTypeAndNoticeUid(isnew, scrap, noticeUid);
	}
	@Override
	public List<Notice> noticeOldScrap(String noticeUid){		
		return noticeDao.findAllByNoticeIsnewAndNoticeTypeAndNoticeUid(!isnew, scrap, noticeUid);
	}
	@Override
	public List<Notice> noticeNewComment(String noticeUid){		
		return noticeDao.findAllByNoticeIsnewAndNoticeTypeAndNoticeUid(isnew, comment, noticeUid);
	}
	@Override
	public List<Notice> noticeOldComment(String noticeUid){		
		return noticeDao.findAllByNoticeIsnewAndNoticeTypeAndNoticeUid(!isnew, comment, noticeUid);
	}

}
