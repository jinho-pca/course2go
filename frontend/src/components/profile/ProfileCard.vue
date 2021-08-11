<template>
  <div class="profile-card">
    <div class="profile-top">
      <div class="profile-imagebox">
        <img :src="profileData.userImage" alt="profile image" class="profile-image">
      </div>
      <div class="profile-content">
        <div class="profile-namebox">
          <div class="profile-name">{{ profileData.userNickname }}</div>
          <!-- v-if(나){프로필 편집} v-elseif(팔로우){팔로우취소} v-else -->
          <div class="profile-follow" v-if="!nickname">
            <router-link to="/profile/modify">프로필 편집</router-link>
          </div>

          <div class="profile-follow" v-if="nickname && !profileData.following" @click="follow">
            <div to="/profile/modify">팔로우</div>
          </div>
          
          <div class="profile-follow" v-if="nickname && profileData.following" @click="unfollow">
            <div to="/profile/modify">언팔로우</div>
          </div>

        </div>
        <div class="profile-introduction">
          <span>{{ profileData.userComment }}</span>
        </div>
      </div>
    </div>
    <div class="profile-bottom">
      <router-link :to="{ path: '/follow' }">
        <div>
          <span class="tag">팔로잉</span>
          <span>{{ profileData.userFollowing }}</span>
        </div>
      </router-link>
      <router-link to="/follow">
        <div>
          <span class="tag">팔로워</span>
          <span>{{ profileData.userFollower }}</span>
        </div>
      </router-link>
      <div>
        <span class="tag">좋아요</span>
        <span>806</span>
      </div>
    </div>
  </div>
</template>

<script>
import "@/components/css/profile/profileCard.css"
import {requestFollow, unfollow} from "@/compositions/follow/follow"
export default {
  name: 'ProfileCard',
  props: {
    profileData: {
      type: Object,
    },
    nickname:{
      type: String,
    }
  },
  methods:{
    follow(){
      requestFollow(this.profileData.userNickname)
    },
    unfollow(){
      unfollow(this.profileData.userNickname)
    }
  }
  
}
</script>