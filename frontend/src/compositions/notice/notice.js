import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'

const { URL, token } = BASE_URL();

export const getNewNotice = () => {

  
	const headers = {
		'Authorization' : token
	}

	axios({
	method: 'get',
	headers: headers,
	url: URL + 'notice/comment/new',
	})
	.then((res) => {
		console.log(res.data);
		return res
	})
	.catch((err) => {
		alert('새 알림을 불러오는데 실패하였습니다.')
		return err
	})
}


