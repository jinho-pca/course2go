import { ref } from 'vue';
// import { useRouter } from 'vue-router';
import axios from 'axios'
import jwt from 'jsonwebtoken'
import { BASE_URL } from '@/compositions/global.js'

const { URL, token } = BASE_URL();

export const profile = () => {
  const who = ref('');
  const profileData = ref({});
  const routeListData = ref([]);
  const visitListData = ref([]);

  const myProfile = (userNickname) => {

    if(!userNickname){
      const user = jwt.decode(token.substr(7))
      userNickname = user.userNickname;
    }
    
    axios({
      method: 'get',
      url: URL + 'user/profile/' + userNickname,
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

  const routeList = (userNickname) => {

    if(!userNickname){
      const user = jwt.decode(token.substr(7))
      userNickname = user.userNickname;
    }

    axios({
      method: 'get',
      url: URL + 'route/list/' + userNickname,
      headers: {
        Authorization: token,
      },
    })
    .then((res) => {
      routeListData.value = res.data.object;
      console.log(res.data.object);
      return res
    })
    .catch((err) => {
      return err
    })
    
  }

  const visitList = (userNickname) => {

    if(!userNickname){
      const user = jwt.decode(token.substr(7))
      userNickname = user.userNickname;
    }

    axios({
      method: 'get',
      url: URL + 'visit/list/' + userNickname,
      headers: {
        Authorization: token,
      },
    
    })
    .then((res) => {
      visitListData.value = res.data.object;
      console.log(res.data.object);
      return res
    })
    .catch((err) => {
      return err
    })
    
  }

  return { who, profileData, routeListData, visitListData, myProfile, routeList, visitList }
}

export const updateProfile = (userComment, userImage) => {
	
	const headers = {
		'Authorization' : localStorage.getItem('Authorization')
	}
	let frm = new FormData();
	frm.append("image", userImage);
  frm.append("comment", userComment);
	console.log(userImage);
	axios({
		method: 'put',
		headers: headers,
		url: URL + 'user/edit',
		data : frm,
	})
	.then((res) => {
		console.log(res.data);
		return res
	})
	.catch((err) => {
		alert(err)
		return err
	})
}