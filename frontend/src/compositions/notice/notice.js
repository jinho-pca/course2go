import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'

const { URL, token } = BASE_URL();

export const getNewNotice = async () => {
  let notice = [];

	const headers = {
		'Authorization' : token
	}
	await axios({
		method: 'get',
		headers: headers,
		url: URL + 'notice/board/new',
		})
		.then((res) => {
			console.log(res.data);
			notice = res.data;
			return res;
		})
		.catch((err) => {
			alert('새 알림을 불러오는데 실패하였습니다.');
			return err;
	})
	return notice;
}

export const getOldNotice = async () => {
  let notice = [];
	const headers = {
		'Authorization' : token
	}
	await axios({
		method: 'get',
		headers: headers,
		url: URL + 'notice/board/old',
		})
		.then((res) => {
			notice = res.data;
			return res;
		})
		.catch((err) => {
			console.log('새 알림을 불러오는데 실패하였습니다.');
			return err;
	})
	return notice;
}

export const getNewRequest = async () => {
  let notice = [];
	const headers = {
		'Authorization' : token
	}
	await axios({
		method: 'get',
		headers: headers,
		url: URL + 'notice/follow/new',
		})
		.then((res) => {
			notice = res.data;
			return res;
		})
		.catch((err) => {
			console.log('새 알림을 불러오는데 실패하였습니다.');
			return err;
	})
	return notice;
}

export const getOldRequest = async () => {
  let notice = [];
	const headers = {
		'Authorization' : token
	}
	await axios({
		method: 'get',
		headers: headers,
		url: URL + 'notice/follow/old',
		})
		.then((res) => {
			notice = res.data;
			return res;
		})
		.catch((err) => {
			console.log('새 알림을 불러오는데 실패하였습니다.');
			return err;
	})
	return notice;
}

export const checkNotice = () => {
	const headers = {
		'Authorization' : token
	}
	axios({
		method: 'post',
		headers: headers,
		url: URL + 'notice/board/update',
		})
		.then((res) => {
			return res;
		})
		.catch((err) => {
			console.log(err);
			return err;
	})
}

export const checkRequest = () => {
	const headers = {
		'Authorization' : token
	}
	axios({
		method: 'post',
		headers: headers,
		url: URL + 'notice/follow/update',
		})
		.then((res) => {
			return res;
		})
		.catch((err) => {
			console.log(err);
			return err;
	})
}