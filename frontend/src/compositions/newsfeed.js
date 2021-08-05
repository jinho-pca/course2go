import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'

const { URL, token } = BASE_URL();

export const newsfeed = () => {
  // const router = useRouter()
  // const store = useStore()

  const getNewsfeed = () => {
    axios({
      method: 'post',
      url: URL + 'newsfeed/read',
      headers: {
        Authorization: `Bearer ${token}`,
      }// mode: 'cors',
    })
    .then((res) => {
      console.log(res)
      return res
    })
    .catch((err) => {
      console.log(err)
      return err
    })
  }
  return { getNewsfeed }
}