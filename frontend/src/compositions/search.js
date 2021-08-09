import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'

const { URL } = BASE_URL();

export const searchPlace = async (key, page) => {

	let result = [];
	
	await axios({
		method: 'get',
		url: URL + 'search/place/' + key + '/' + page,
		// url: 'http://localhost:8095/search/place/' + key + '/' + page,
	})
	.then((res) => {
		result = res.data;
		return res
	})
	.catch((err) => {
		alert('검색 결과가 없습니다.')
		return err
	})
	return result;
}