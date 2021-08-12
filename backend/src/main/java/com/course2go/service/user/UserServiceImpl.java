package com.course2go.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.course2go.dao.FollowDao;
import com.course2go.dao.NoticeDao;
import com.course2go.model.user.UserDto;
import com.course2go.service.follow.FollowListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course2go.dao.UserDao;
import com.course2go.model.user.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	FollowDao followDao;
	
	@Autowired
	NoticeDao noticeDao;
	
	@Autowired
	FollowListService followListService;
	
	@Override
	public List<UserDto> searchUser(String requestNickname, String keyword) {
		List<User> tmp = userDao.findByUserNameOrUserNicknameContaining(keyword);
		List<UserDto> result = new ArrayList<>(); // 유저검색결과를 포함한 결과
		
		User requestUser = userDao.getUserByUserNickname(requestNickname).get();
		
		for (int i = 0; i < tmp.size(); i++) {
			User tmpUser = tmp.get(i);
			UserDto userDto = new UserDto();
			if(requestNickname.equals(tmpUser.getUserNickname())){
				// 검색한 이름을 포함하는 유저가 본인인 경우
				tmp.remove(i);
				continue;
			}else{
				userDto.setUserName(tmpUser.getUserName());
				userDto.setUserNickname(tmpUser.getUserNickname());
				userDto.setUserImage(tmpUser.getUserImage());
				
				// Follow하고 있는 경우 followState 를 1로 설정
				if(followDao.existsByFollowFromUidAndFollowToUid(requestUser.getUid(), tmpUser.getUid())) {
					userDto.setFollowState(1);
				} 
				// Follow 신청 하고 있는 경우 followState 를 2로 설정
				else if(noticeDao.existsByNoticeTypeAndNoticeUidAndNoticeFromUid(1, tmpUser.getUid(), requestUser.getUid())) {
					userDto.setFollowState(2);
				}
				else {
					userDto.setFollowState(0);
				}
			}
			result.add(userDto);
		}

		return result;
	}

	@Override
	public String getUserNicknameByUid(String uid) {
		User user = userDao.findById(uid).get();
		return user.getUserNickname();
	}

	@Override
	public UserDto getUserDtoByUid(String uid) {
		User user = userDao.findById(uid).get();
		return new UserDto(user.getUserName(), user.getUserNickname(), user.getUserImage());
	}

	@Override
	public String getUidByUserNickname(String userNickname) {
		return userDao.findUserByUserNickname(userNickname).get().getUid();
	}
}
