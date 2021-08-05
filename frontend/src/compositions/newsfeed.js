// import { ref } from 'vue';
// import { useRouter } from 'vue-router';
// import { useStore } from 'vuex';
import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'

const URL = BASE_URL()

export const newsfeed = () => {
  // const router = useRouter()
  // const store = useStore()

  const getNewsfeed = () => {
    axios({
      method: 'post',
      url: URL + 'newsfeed/read',
      headers: 'BEARER eyJyZWdEYXRlIjoxNjI4MDQzMzM0MTY4LCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiI4NDUxNzk3Zi1hOGNlLTRkZGYtODcyNy1lZjhmMTM3OTUwOWYiLCJ1c2VyTmlja25hbWUiOiJqaW5obzA2MjgiLCJ1c2VyRW1haWwiOiJwX2NhNTQxMkBuYXZlci5jb20iLCJleHAiOjE2MzA2MzUzMzR9.Vy7_7SNaZBbd8xFoYrvywl10oVn4gokg2sh7mXOBxMw',
      // mode: 'cors',
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