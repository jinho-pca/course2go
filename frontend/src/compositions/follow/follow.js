import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'

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
	url: URL + 'follow/deny/'+id,
	})
	.then((res) => {
		console.log('팔로우 승인 완료');
		return res
	})
	.catch((err) => {
		alert('팔로우 거부 실패')
		return err
	})
}