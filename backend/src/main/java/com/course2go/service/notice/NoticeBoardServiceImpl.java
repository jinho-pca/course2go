package com.course2go.service.notice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.BoardDao;
import com.course2go.dao.CommentDao;
import com.course2go.dao.NoticeDao;
import com.course2go.model.board.Board;
import com.course2go.model.comment.Comment;
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
	@Autowired
	CommentDao commentDao;
	@Autowired
	BoardDao boardDao;
	
	
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
		List<Notice> noticeList = noticeDao.findAllByNoticeIsnewAndNoticeTypeInAndNoticeUid(isnew, board, noticeUid);
		List<NoticeDto> noticeDtoList = new ArrayList<NoticeDto>();
		
		for (Notice notice : noticeList) {
			Optional<Comment> comment = commentDao.findById(notice.getNoticeNnid());
			if(!comment.isPresent()) {
				noticeDao.delete(notice);
				continue;
			}
			
			Optional<Board> board = boardDao.findById(comment.get().getCommentBid());
			if(!board.isPresent()) {
				noticeDao.delete(notice);
				continue;
			}
			
			String userNickname = userService.getUserNicknameByUid(notice.getNoticeFromUid());
			if(userNickname == null) {
				noticeDao.delete(notice);
				continue;
			}
			
			if(board.get().getBoardType()) {
				noticeDtoList.add(new NoticeDto(notice,userNickname, board.get().getBid()));				
			} else {
				noticeDtoList.add(new NoticeDto(notice,userNickname, board.get().getBid(), board.get().getBoardTid()));
			}
		}
		return noticeDtoList;
	}
	
	@Override
	public List<NoticeDto> noticeOldBoard(String noticeUid) {
		List<Notice> noticeList = noticeDao.findAllByNoticeIsnewAndNoticeTypeInAndNoticeUid(!isnew, board, noticeUid);
		List<NoticeDto> noticeDtoList = new ArrayList<NoticeDto>();
		
		for (Notice notice : noticeList) {
			Optional<Comment> comment = commentDao.findById(notice.getNoticeNnid());
			if(!comment.isPresent()) {
				noticeDao.delete(notice);
				continue;
			}
			
			Optional<Board> board = boardDao.findById(comment.get().getCommentBid());
			if(!board.isPresent()) {
				noticeDao.delete(notice);
				continue;
			}
			
			String userNickname = userService.getUserNicknameByUid(notice.getNoticeFromUid());
			if(userNickname == null) {
				noticeDao.delete(notice);
				continue;
			}
			
			if(board.get().getBoardType()) {
				noticeDtoList.add(new NoticeDto(notice,userNickname, board.get().getBid()));				
			} else {
				noticeDtoList.add(new NoticeDto(notice,userNickname, board.get().getBid(), board.get().getBoardTid()));
			}
		}
		
//		List<NoticeDto> newBoardListDto = newBoardList.stream().map(entityList -> modelmapper.map(entityList, NoticeDto.class)).collect(Collectors.toList());
//		for (NoticeDto noticeDto : newBoardListDto) {
//			noticeDto.setNoticeFromUserNickname(userService.getUserNicknameByUid(noticeDto.getNoticeFromUid()));
//			Optional<Comment> comment = commentDao.findById(noticeDto.getNoticeNnid());
//			if(comment.isPresent()) {
//				noticeDto.setBid(comment.getCommentBid());
//				Board board = boardDao.getById(comment.getCommentBid());
//				if(!board.isBoardType()) {
//					noticeDto.setVid(board.getBoardTid());
//				}				
//			} else {
//				noticeDto = null;
//			}
//			
//		}
		
		return noticeDtoList;
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
