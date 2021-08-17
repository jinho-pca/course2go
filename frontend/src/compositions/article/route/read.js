import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'
import { ref } from 'vue'

export const readRoute = () => {
  const { URL, token } = BASE_URL()
  const articleRoute = ref(
    {
      routeResponse: {
        routeStartDate: "",
        routeEndDate: "",
        routeContent: ""
      },
      boardResponse: {
        boardTitle: "",
        boardLike: '',
        boardStar: '',
        boardTid: '',
        boardType: true,
        boardTime: ""
      },
      containSpots: [{
          place: {
            pid: 5,
            placeName: "섶섬",
            placeAddress: "제주특별자치도 서귀포시 보목동 산 1",
            placeLat: '',
            placeLng: '',
            placeDataDate: "2015-12-31",
            placeType: "지명관련",
          },
          visit: null
      }],
      userDto: {
        // userImage: '',
        userNickname: ''
      },
    }
  )
  const read = (bid) => {
    axios({
      method: 'get',
      url: URL + `route/read/${bid}`,
      headers: {
        Authorization: token,
      }
    })
    .then((res) => {
      articleRoute.value = res.data.object;
      console.log(res.data.object)
      return res.data
    })
    .then((res) => {
      console.log(res)
    })
    .catch((err) => {
      console.log(err)
      alert('동선을 불러오는데 실패했습니다.')
      return err
    })
  }
    return { articleRoute, read }
}
