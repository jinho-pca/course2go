import { ref } from 'vue';
// import { useRouter } from 'vue-router';
import axios from 'axios'
import jwt from 'jsonwebtoken'
import { BASE_URL } from '@/compositions/global.js'

const { URL, token } = BASE_URL();

export const profile = () => {
  const followingListData = ref([]);
  const followerListData = ref([]);

  const followingList = (userNickname) => {

    if(!userNickname){
      const user = jwt.decode(token.substr(7))
      userNickname = user.userNickname;
    }
    
    axios({
      method: 'get',
      url: URL + 'follow/follower/' + userNickname,
      headers: {
        Authorization: token,
      }
    })
    .then((res) => {
      profileData.value = res.data.object
      who.value = res.data.data.substr(0, 1)
      if (who.value === '본') {
        who.value = 1
      } else {
        who.value = 0
      }
      return res
    })
    .catch((err) => {
      return err
    })
  }

  

  return { who, profileData, routeListData, visitListData, myProfile, routeList, visitList }
}

