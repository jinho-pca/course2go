
import axios from 'axios';

export const searchPlace = async (key) => {

	let result = [];
	
	await axios({
		method: 'get',
		url: 'http://13.209.13.47:8080/search/place/' + key,
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
