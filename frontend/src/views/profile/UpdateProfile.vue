<template>
  <div class="update-profile">
    <router-link to="/profile">
      <i class="fas fa-chevron-left"></i>
    </router-link>
    <span>프로필 편집</span>
    <div>
      <div class="update-image">
        <div>{{$route.params.userNickname}}</div>
        <div>
          <div>
            <img :src="userImagePreview" alt="profile image" class="profile-image">
          </div>
        </div>
        <div>
          <label for="user-image-btn">프로필 사진 바꾸기</label>
          <input 
            type="file" 
            id="user-image-btn" 
            ref="files"
            style="display:none"
            accept="image/jpeg, image/jpg, image/png"
            @change="changeImage"
          />
        </div>
      </div>
      <div class="update-message">
        <div>상태 메시지</div>
        <div>
          <input type="text" v-model="userComment">
          <i class="fas fa-edit"></i>
        </div>
      </div>
      <div class="update-btn">
        <button @click="save"> 저장 </button>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/profile/update-profile.css';
import {updateProfile} from '@/compositions/profile'
export default {
  name: 'UpdateProfile',
  methods:{
    save(){
      updateProfile(this.userComment, this.userImage);
    },
    changeImage(){
      console.log(this.$refs.files);

			let file = 
			{
				file: this.$refs.files.files[0],
				preview: URL.createObjectURL(this.$refs.files.files[0]),
			}
      this.userImage = file.file;
      this.userImagePreview = file.preview
    }
  },
  data: function(){
    return{
      userImage: null,
      userComment: this.$route.params.userComment,
      userImagePreview: this.$route.params.userImage
    }
  }
}
</script>