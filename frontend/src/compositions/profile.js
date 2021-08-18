import { ref } from 'vue';
// import { useRouter } from 'vue-router';
import axios from 'axios'
import jwt from 'jsonwebtoken'
import { BASE_URL } from '@/compositions/global.js'
import { useRouter } from 'vue-router'

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

  const articleAmount = ref(0)

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
      console.log(routeListData.value.length)
      articleAmount.value += routeListData.value.length;
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
      console.log(visitListData.value.length)
      articleAmount.value += visitListData.value.length;
      return res
    })
    .catch((err) => {
      return err
    })
    
  }

  return { who, profileData, routeListData, visitListData, myProfile, routeList, visitList, articleAmount }
}

export const updateProfile = () => {
  const router = useRouter();
  const user = jwt.decode(token.substr(7))
  const userNickname = ref('')
  const userImage = ref({
    file: '',
    preview: ''
  })
  const userComment = ref('')

  axios({
    method: 'get',
    url: URL + 'user/profile/' + user.userNickname,
    headers: {
      Authorization: token,
    }
  })
  .then((res) => {
    userNickname.value = res.data.object.userNickname
    userImage.value.preview = res.data.object.userImage
    userComment.value = res.data.object.userComment
    return res
  })
  .catch((err) => {
    return err
  })

  const profileUpdate = (image, comment) => {
    let frm = new FormData();
    frm.append("image", image.file);
    // frm.append("comment", comment);
    // const data = {
    //   'image': image,
    //   'comment': comment
    // }
    axios({
      method: 'put',
      url: URL + 'user/edit',
      headers: {
        Authorization: token,
      },
      data: frm,
      params: {
        'comment': comment
      }
    })
    .then((res) => {
      router.push('/profile')
      return res
    })
    .then((err) => {
      console.log(err)
      return err
    })
  }

  const addImage = () => {
    var photoFile = document.getElementById("file");
    console.log(photoFile);

    userImage.value = {
      file: '',
      preview: URL.createObjectURL(userImage.value.file),
    }
  }

  return { profileUpdate, userNickname, userImage, userComment, addImage }
}