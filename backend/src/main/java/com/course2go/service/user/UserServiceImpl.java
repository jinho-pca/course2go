package com.course2go.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.course2go.dao.FollowDao;
import com.course2go.model.user.UserDto;
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

	@Override
	public Optional<User> getUserByUserNickname(String nickname) {
		return userDao.getUserByUserNickname(nickname);
	}

	@Override
	public List<UserDto> searchUser(String requestNickname, String userName) {
		String followFromUid = userDao.findUserByUserNickname(requestNickname).get().getUid(); // 검색한사람의 uid
		List<UserDto> result = new ArrayList<>(); // 유저검색결과를 포함한 결과
		List<User> userList = userDao.findAllByUserName(userName); // 검색한 이름을 가진 유저들의 리스트

		for (int i = 0; i < userList.size(); i++) {
			User tmpUser = userList.get(i);
			UserDto userDto = new UserDto();
			if(tmpUser.getUserNickname().equals(requestNickname)){
				// 유저검색에서 받아온 유저가 요청한 본인인 경우
				userList.remove(i);
				continue;
			}else{
				userDto.setUserName(tmpUser.getUserName());
				userDto.setUserNickname(tmpUser.getUserNickname());
				userDto.setUserImage(tmpUser.getUserImage());
			}

			if(followDao.existsByFollowFromUidAndFollowToUid(followFromUid, tmpUser.getUid())){
				// 검색의 주체유저가 검색한 사람을 팔로우 하고 있는 경우
				userDto.setIsFollow(true);
			}else{
				// 검색의 주체유저가 검색한 사람을 팔로우 하고 있지 않은 경우
				userDto.setIsFollow(false);
			}
			result.add(userDto);
		}
		return result;
	}
}
