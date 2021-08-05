import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'

const { URL, token } = BASE_URL();

export const writeRoute = (title, routeStartDate, routeEndDate, routeContent, routePid) => {
	const data = {
		title,
		routeStartDate,
		routeEndDate,
		routeContent,
		routePid
	};
  
	const headers = {
		'Authorization' : token
	}

	axios({
	method: 'post',
	headers: headers,
	url: URL + 'route/write',
			data: data
	})
	.then((res) => {
		console.log(res.data);
		return res
	})
	.catch((err) => {
		alert('검색 결과가 없습니다.')
		return err
	})
}
