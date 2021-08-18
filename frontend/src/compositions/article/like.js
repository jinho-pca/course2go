import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'

export const like = (bid) => {
  const { URL, token } = BASE_URL()
  const headers = {
    'Authorization' : token
  }
  axios({
    method: 'post',
    url: URL + `board/like/` + bid,
    headers: headers,
  })
  .then((res) => {
    console.log('좋아요 성공')

      return res
  })
  .catch((err) => {
      console.log('좋아요 실패')
      return err
  })
}
