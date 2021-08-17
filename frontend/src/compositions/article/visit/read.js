import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'
import { ref } from 'vue'

export const readVisit = () => {
    const { URL, token } = BASE_URL()
    const articleVisit = ref(
      {
        visitResponse: {
          visitStartDate: "",
          visitEndDate: "",
          visitContent: ""
        },
        boardResponse: {
          boardTitle: "",
          boardLike: '',
          boardStar: '',
          boardTid: '',
          boardType: false,
          boardTime: ""
        },
        userDto: {
          // userImage: '',
          userNickname: ''
        },
      }
    )
    const read = (bid) => {
      axios({
        method: 'get',
        url: URL + `visit/read/${bid}`,
        headers: {
          Authorization: token,
        }
      })
      .then((res) => {
        articleVisit.value = res.data.object;
        console.log(res.data.object)
        return res.data
      })
      .then((res) => {
        console.log(res)
      })
      .catch((err) => {
        console.log(err)
        alert('글을 불러오는데 실패했습니다.')
        return err
      })
    }
      return { articleVisit, read }
  }
  
