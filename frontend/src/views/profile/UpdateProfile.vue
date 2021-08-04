<template>
  <div class="update-profile">
    <router-link to="/profile">
      <i class="fas fa-chevron-left"></i>
    </router-link>
    <span>프로필 편집</span>
    <div>
      <div class="update-image">
        <div>Goki</div>
        <div>
          <div>
            <img src="@/assets/images/map.jpg" alt="profile image" class="profile-image">
          </div>
        </div>
        <div>
          <span>프로필 사진 바꾸기</span>
        </div>
      </div>
      <div class="update-message">
        <div>상태 메시지</div>
        <div>
          <span>안녕하세요</span>
          <i class="fas fa-edit"></i>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/profile/update-profile.css';

export default {
  name: 'UpdateProfile'
}
</script>