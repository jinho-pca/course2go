import axios from 'axios';
import { ref } from 'vue';
import { BASE_URL } from '@/compositions/global.js'

const { URL, token } = BASE_URL();

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

export const search = () => {

	const page = ref(1)

	const onClickSelect = (e) => {
    const isActive = e.currentTarget.className.indexOf("active") !== -1;
    if (isActive) {
      e.currentTarget.className = "search-select";
    } else {
      e.currentTarget.className = "search-select active";
    }
  }

	const onClickOption = (e) => {
    const selectedValue = e.currentTarget.innerHTML;
    document.querySelector(".search-tab .search-text").innerHTML = selectedValue;
		if (e.currentTarget.value == 1 && page.value == 2) {
			page.value = 1
		} else if (e.currentTarget.value == 2 && page.value == 1) {
			page.value = 2
		}
  }

	const users = ref([])

	const searchUser = (word) => {
		axios({
			method: 'get',
			url: URL + 'search/user/' + word,
      headers: {
        Authorization: token,
      }
		})
		.then((res) => {
			console.log(res.data)
			users.value = res.data
			return res
		})
		.catch((err) => {
			console.log(err)
		})
	}

	// const searchRoute = (word) => {
	// 	continue
	// }

	return { onClickSelect, onClickOption, page, searchUser, users }
}