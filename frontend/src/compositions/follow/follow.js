import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'
import jwt from 'jsonwebtoken'
const { URL, token } = BASE_URL();

export const agreeFollow = (id) => {
	
	const headers = {
		'Authorization' : token
	}

	axios({
	method: 'post',
	headers: headers,
	url: URL + 'follow/agree/',
  params:{
    noticeId: id
  }
	})
	.then((res) => {
		console.log('팔로우 승인 완료');
		return res
	})
	.catch((err) => {
		alert('팔로우 승인 실패')
		return err
	})
}

export const denyFollow = (id) => {
	
	const headers = {
		'Authorization' : token
	}

	axios({
	method: 'delete',
	headers: headers,
	url: URL + 'follow/deny',
  params:{
    noticeId: id
  }
	})
	.then((res) => {
		console.log('팔로우 거부 완료');
		return res
	})
	.catch((err) => {
		alert('팔로우 거부 실패')
		return err
	})
}

export const requestFollow = (followToNickname) => {
	
	const user = jwt.decode(token.substr(7))
	const followFromNickname = user.userNickname;

	const headers = {
		'Authorization' : token
	}

	axios({
	method: 'post',
	headers: headers,
	url: URL + 'follow/follow',
  params:{
    followFromNickname: followFromNickname,
		followToNickname: followToNickname
  }
	})
	.then((res) => {
		console.log('팔로우 신청 완료');
		return res
	})
	.catch((err) => {
		alert('팔로우 신청 실패')
		return err
	})
}

export const unfollow = (followToNickname) => {
	
	const user = jwt.decode(token.substr(7))
	const followFromNickname = user.userNickname;

	const headers = {
		'Authorization' : token
	}

	axios({
	method: 'delete',
	headers: headers,
	url: URL + 'follow/unfollow',
  params:{
    followFromNickname: followFromNickname,
		followToNickname: followToNickname
  }
	})
	.then((res) => {
		console.log('언팔로우 완료');
		return res
	})
	.catch((err) => {
		alert('언팔로우 실패')
		return err
	})
}