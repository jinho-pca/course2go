import { ref } from 'vue';
// import { useRouter } from 'vue-router';
import axios from 'axios'
import jwt from 'jsonwebtoken'
import { BASE_URL } from '@/compositions/global.js'

const { URL, token } = BASE_URL();

export const follow = () => {
  const followingListData = ref([]);
  const followerListData = ref([]);

  const followingList = (userNickname) => {
    if(!userNickname){
      const user = jwt.decode(token.substr(7))
      userNickname = user.userNickname;
    }
    
    
    axios({
      method: 'get',
      url: URL + 'follow/following',
      params:{
        nickname: userNickname,
      },
      headers: {
        Authorization: token,
      }
    })
    .then((res) => {
      followingListData.value = res.data.object
      return res
    })
    .catch((err) => {
      return err
    })
  }

  const followerList = (userNickname) => {
    if(!userNickname){
      const user = jwt.decode(token.substr(7))
      userNickname = user.userNickname;
    }
    
    axios({
      method: 'get',
      url: URL + 'follow/follower',
      params:{
        nickname: userNickname,
      },
      headers: {
        Authorization: token,
      }
    })
    .then((res) => {
      followerListData.value = res.data.object
      return res
    })
    .catch((err) => {
      return err
    })
  }

  return { followingList, followingListData, followerList, followerListData}
}

