import axios from 'axios';
import { ref } from 'vue';
import { BASE_URL } from '@/compositions/global.js'

const { URL, token } = BASE_URL();

export const searchPlace = async (key, page) => {

	let result = [];
	
	await axios({
		method: 'get',
		url: URL + 'search/place/' + key + '/' + page,
		headers: {
			Authorization: token,
		}
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

	const routes = ref([])
	
	const showModal = ref(false)
	const placeList = ref([])
	const placePidList = ref([])
	const articles = ref([])
	const filteredArticles = ref([])

	const searchRoute = async (route) => {
		if (route.value.length < 1) {
			articles.value = []
			filteredArticles.value = []
			return
		}

		const places = ref({
			pid1: -1,
			pid2: -1,
			pid3: -1,
			pid4: -1,
			pid5: -1,
			pid6: -1,
			pid7: -1,
			pid8: -1,
			pid9: -1,
		})

		for (let i=0; i < route.value.length; i++) {
			places.value[`pid${i+1}`] = parseInt(route.value[i])
		}
		
		await axios({
			method: 'get',
			url: URL + 'search/route',
			params: places.value,
			headers: {
        Authorization: token,
      }
		})
		.then((res) => {
			articles.value = res.data.object
			filteredArticles.value = articles.value
			return res
		})
		.catch((err) => {
			console.log(err)
			return err
		})
		
		if (route.value.length == 1) {
			await axios({
				method: 'get',
				url: URL + `search/visit/${places.value.pid1}`,
				headers: {
					Authorization: token,
				},
			})
			.then((res) => {
				console.log(articles.value)
				console.log('res', res.data.object)
				if (articles.value) {
					articles.value = [...articles.value.concat(res.data.object)].reverse()
					filteredArticles.value = articles.value
				} else {
					articles.value = res.data.object
					filteredArticles.value = articles.value
				}
				return res
			})
			.catch((err) => {
				return err
			})
		}
	}
	return { onClickSelect, onClickOption, page,
		searchUser, users,
		searchRoute, routes, showModal, placeList, placePidList, articles, filteredArticles }
}