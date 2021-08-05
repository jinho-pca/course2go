package com.course2go.service.newsfeed;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.model.board.BoardDto;
import com.course2go.model.newsfeed.NewsfeedDto;
import com.course2go.service.board.BoardService;
import com.course2go.service.comment.CommentService;
import com.course2go.service.follow.FollowListService;
import com.course2go.service.route.RouteService;
import com.course2go.service.user.UserService;
import com.course2go.service.visit.VisitService;

@Service
public class NewsfeedServiceImpl implements NewsfeedService{

	@Autowired
	BoardService boardService;
	@Autowired
	RouteService routeService;
	@Autowired
	VisitService visitService;
	@Autowired
	CommentService commentService;
	@Autowired
	FollowListService followlistService;
	@Autowired
	UserService userService;
	
	@Override
	public List<NewsfeedDto> getListNewsfeed(String uid) {
		List<NewsfeedDto> list = new LinkedList<NewsfeedDto>();
		Iterable<String> uids = followlistService.getFollowingListByUid(uid);
		List<BoardDto> boards = boardService.getListbyUids(uids);
		for (BoardDto boardDto : boards) {
			boardDto.setBoardWriterNickname(userService.getUserNicknameByUid(boardDto.getBoardWriterUid()));
			NewsfeedDto newsfeed = new NewsfeedDto();
			newsfeed.setBoard(boardDto);
			if (boardDto.isBoardType()) {
				newsfeed.setThing(routeService.readRoute(boardDto.getBoardTid()));				
			} else {
				newsfeed.setThing(visitService.readVisit(boardDto.getBoardTid()));
			}
			newsfeed.setBestcomment(commentService.getCommentBiggestLike(boardDto.getBid()));
			list.add(newsfeed);
		}
		return list;
	}

}
