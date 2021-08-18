import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'

export const deleteBoard = (bid) => {
  const { URL, token } = BASE_URL()
  console.log(bid);
  const headers = {
    'Authorization' : token
  }
  axios({
    method: 'delete',
    url: URL + `board/` + bid,
    headers: headers,
  })
  .then((res) => {
    console.log('삭제 성공')
      return res
  })
  .catch((err) => {
      console.log('삭제 실패')
      return err
  })
}