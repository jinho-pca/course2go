import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'

export const deleteBoard = async (bid) => {
  const { URL, token } = BASE_URL()
  console.log(bid);
  const headers = {
    'Authorization' : token
  }
  await axios({
    method: 'delete',
    url: URL + `board/` + bid,
    headers: headers,
  })
  .then((res) => {
    console.log('삭제 성공')
    alert('글이 삭제되었습니다.')
      return res
  })
  .catch((err) => {
      console.log('삭제 실패')
      return err
  })
  // this.$router.push({name: 'Newsfeed'});
}