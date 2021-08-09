import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'
import { ref } from 'vue'

const { URL, token } = BASE_URL();

export const newsfeed = () => {
  // const router = useRouter()
  // const store = useStore()
  const newsfeeds = ref({});
  let filteredArticle = ref([]);

  const getNewsfeed = () => {
    axios({
      method: 'get',
      url: URL + 'newsfeed/read',
      headers: {
        Authorization: token,
      }// mode: 'cors',
    })
    .then((res) => {
      newsfeeds.value = res.data.object
      console.log(res.data.object)
      return res
    })
    .catch((err) => {
      console.log(err)
      return err
    })
  }

  const articleFilter = (filter) => {
    filteredArticle.value = newsfeeds.value.filter(x => {
      console.log(x, filter)
      return x === filter
    })
  }

  return { getNewsfeed, newsfeeds
    , articleFilter, filteredArticle 
  }
}