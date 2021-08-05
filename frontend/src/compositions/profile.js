// import { ref } from 'vue';
// import { useRouter } from 'vue-router';
import axios from 'axios'
import jwt from 'jsonwebtoken'
import { BASE_URL } from '@/compositions/global.js'

const { URL, token } = BASE_URL()

export const profile = () => {
  const myProfile = () => {
    const user = jwt.decode(token)
    
    axios({
      method: 'get',
      url: URL + 'user/profile/' + user.userNickname,
      headers: {
        Authorization: `Bearer ${token}`,
      }
    })
    .then((res) => {
      console.log(res.data.object)
      return res
    })
    .catch((err) => {
      console.log(err)
      return err
    })
  }
  return { myProfile }
}