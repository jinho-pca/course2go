
import axios from 'axios';

export const writeRoute = (title, routeStartDate, routeEndDate, routeContent, routePid) => {
	const data = {
		title,
		routeStartDate,
		routeEndDate,
		routeContent,
		routePid
	};
  
	const headers = {
		'Authorization' : localStorage.getItem('Authorization')
	}

	axios({
	method: 'post',
	headers: headers,
	url: 'http://13.209.13.47:8080/route/write',
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
