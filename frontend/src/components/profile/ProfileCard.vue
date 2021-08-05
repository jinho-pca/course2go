<template>
  <div class="profile-card">
    <div class="profile-top">
      <div class="profile-imagebox">
        <img src="@/assets/images/map.jpg" alt="profile image" class="profile-image">
      </div>
      <div class="profile-content">
        <div class="profile-namebox">
          <div class="profile-name">Goki</div>
          <!-- v-if(나){프로필 편집} v-elseif(팔로우){팔로우취소} v-else -->
          <div class="profile-follow">
            <router-link to="/profile/modify">프로필 편집</router-link>
          </div>
        </div>
        <div class="profile-introduction">
          <span>버그를 잡아라</span>
        </div>
      </div>
    </div>
    <div class="profile-bottom">
      <router-link :to="{ path: '/follow' }">
        <div>
          <span class="tag">팔로잉</span>
          <span>2789</span>
        </div>
      </router-link>
      <router-link to="/follow">
        <div>
          <span class="tag">팔로워</span>
          <span>153</span>
        </div>
      </router-link>
      <div>
        <span class="tag">좋아요</span>
        <span>1000</span>
      </div>
    </div>
  </div>
</template>

<script>
import "@/components/css/profile/profileCard.css"
export default {
  name: 'ProfileCard'
}
</script>