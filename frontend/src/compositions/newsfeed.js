import axios from 'axios';
import { BASE_URL } from '@/compositions/global.js'
import { ref } from 'vue'

const { URL, token } = BASE_URL();

export const newsfeed = () => {
  // const router = useRouter()
  // const store = useStore()
  const newsfeeds = ref({});
  let filteredArticle = ref([]);
  let filteredNewsfeeds = ref({});

  const getNewsfeed = () => {
    axios({
      method: 'get',
      url: URL + 'newsfeed/read',
      headers: {
        Authorization: token,
      }
    })
    .then((res) => {
      newsfeeds.value = res.data.object
      filteredNewsfeeds.value = res.data.object
      return res
    })
    .catch((err) => {
      console.log(err)
      return err
    })
  }

  const onClickSelect = (e) => {
    const isActive = e.currentTarget.className.indexOf("active") !== -1;
    if (isActive) {
      e.currentTarget.className = "select";
    } else {
      e.currentTarget.className = "select active";
    }
  }
  
  const onClickOption = (e) => {
    const selectedValue = e.currentTarget.innerHTML;
    document.querySelector(".tab .text").innerHTML = selectedValue;
    if (e.currentTarget.value === 1) {
      filteredNewsfeeds.value = newsfeeds.value
    } else if (e.currentTarget.value === 2) {
      filteredNewsfeeds.value = newsfeeds.value.filter(x => {
        return x.board.boardType == true
      })
    } else {
      filteredNewsfeeds.value = newsfeeds.value.filter(x => {
        return x.board.boardType == false
      })
    }
  }
  
  return { getNewsfeed, newsfeeds,
    onClickSelect, onClickOption, filteredArticle, filteredNewsfeeds 
  }
}