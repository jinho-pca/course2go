package com.course2go.service.comment;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.CommentDao;
import com.course2go.model.comment.Comment;
import com.course2go.model.comment.CommentDto;
import com.course2go.model.comment.CommentWriteRequest;
import com.course2go.service.user.UserService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDao commentDao;
	@Autowired
	UserService userService;

	ModelMapper modelmapper;
	public CommentServiceImpl() {
		modelmapper = new ModelMapper();
	}

	@Override
	public void writeComment(String commentWriterUid, CommentWriteRequest request) {
		writeComment(request.getCommentParent(), request.getCommentBid(), 0, request.getCommentContent(), commentWriterUid);
	}
	
	@Override
	public void writeComment(Integer commentParent, Integer commentBid, Integer commentLike, String commentContent, String commentWriterUid) {
		commentDao.save(Comment.builder(commentParent, commentBid, commentLike, commentContent, commentWriterUid).build());
	}
	
	@Override
	public List<CommentDto> readSortedComment(Integer commentBid) {
		List<CommentDto> commentList = new LinkedList<CommentDto>();
		List<CommentDto> rawList = readComment(commentBid);
		for (CommentDto commentDto : rawList) {
			int parent = commentDto.getCommentParent();
			if (parent==-1) {
				commentDto.setCommentDepth(0);
				commentList.add(commentDto);
				continue;
			}
			int index = 0;
			boolean sawParent = false;
			for (CommentDto sortedCommentDto : commentList) {
				if (sawParent) {
					if (sortedCommentDto.getCommentParent() != parent) {
						break;
					}
					index++;
				} else {
					if (sortedCommentDto.getCid()==parent) {
						sawParent=true;
						commentDto.setCommentDepth(sortedCommentDto.getCommentDepth()+1);
						commentDto.setCommentWriterNickname(userService.getUserNicknameByUid(commentDto.getCommentWriterUid()));
					}
					index++;
				}
			}
			commentList.add(index, commentDto);
		}
		return commentList;
	}

	@Override
	public List<CommentDto> readComment(Integer commentBid) {
		List<Comment> list = commentDao.findAllByCommentBid(commentBid);
		return list.stream().map(com -> modelmapper.map(com, CommentDto.class)).collect(Collectors.toList());
	}

	@Override
	public CommentDto getCommentBiggestLike(Integer commentBid) {
		List<Comment> list = commentDao.findAllByCommentBidOrderByCommentLikeDesc(commentBid);
		if (list.size()==0) {
			return null;			
		}
		Comment comment = list.get(0);
		return new CommentDto(comment.getCid(), comment.getCommentParent(), comment.getCommentBid(), comment.getCommentLike(), comment.getCommentContent(), comment.getCommentWriterUid(), comment.getCommentTime(), null, userService.getUserNicknameByUid(comment.getCommentWriterUid()));
	}

}
