<template>
  <div class="update-profile">
    <router-link to="/profile">
      <i class="fas fa-chevron-left"></i>
    </router-link>
    <span>프로필 편집</span>
    <div>
      <div class="update-image">
        <div>{{ userNickname }}</div>
        <div>
          <div>
            <img :src="userImage.preview" alt="profile image" class="profile-image" v-if="userImage.preview">
            <img src="@/assets/images/profile.png" alt="profile image" class="profile-image" v-else>
          </div>
        </div>
        <div>
          <label class="profile-image-add" for="profile-image-btn">이미지 추가</label>
          <input 
            type="file"
            id="profile-image-btn"
            ref="editImage"
            style="display:none"
            accept="image/jpeg, image/jpg, image/png"
            @change="addImage"
            />
        </div>
      </div>
      <div class="update-message">
        <div>상태 메시지</div>
        <div>
          <input type="text" class="update-comment" id="update-comment" v-model="userComment">
          <i class="fas fa-edit"></i>
        </div>
      </div>
    </div>
    <div class="align-buttons">
      <button class="update-button" @click="profileUpdate(userImage, userComment)">확인</button>
      <button class="update-button" @click="back">취소</button>
    </div>
  </div>
</template>

<script>
import '@/assets/css/profile/update-profile.css';
import { updateProfile } from '@/compositions/profile.js';
import { ref } from 'vue'

export default {
  name: 'UpdateProfile',
  setup() {
    const { profileUpdate, userNickname, userImage, userComment } = updateProfile()
    // const editComment = () => {
    //   const editInput = document.querySelector('#update-comment')
    //   console.log(editInput)
    //   editInput.readOnly = false
    // }
    const editImage = ref(null);
    const addImage = () => {
      const image = {
        file: editImage.value.files[0],
				preview: URL.createObjectURL(editImage.value.files[0]),
      }
      userImage.value = image
    }
    const back = () => {
      history.back()
    }
    return { profileUpdate, userNickname, userImage, userComment, addImage, editImage, back }
  }
}
</script>