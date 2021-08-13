<template>
  <div class="follow-card">
    <router-link :to="{ path: '/profile', query: {nickname : userInfo.userNickname}}">
      <div class="card-left">
        <img :src="userInfo.userImage" alt="">
        <div class="follow-user-info">
          <div>
            {{userInfo.userNickname}}
          </div>
          <div>
            {{userInfo.userName}}
          </div>
        </div>
      </div>
    </router-link>
    <div class="card-right">
      <div class="follow-button" v-if="(userInfo.followState == 0 && followState == -1) || followState == 0" @click="follow">
        <div>팔로우</div>
      </div>
      
      <div class="follow-button" v-if="(userInfo.followState == 1 && followState == -1) || followState == 1" @click="unfollow">
        <div>언팔로우</div>
      </div>

      <div class="follow-button" v-if="(userInfo.followState == 2 && followState == -1) || followState == 2">
        <div>신청중</div>
      </div>
    </div>
  </div>
</template>

<script>
import "@/components/css/profile/follow-card.css"
import {requestFollow, unfollow} from "@/compositions/follow/follow"
export default {
  name: 'FollowCard',
  props:{
    userInfo: {
      type: Object,
    }
  },
  methods:{
    follow(){
      this.followState = 2;
      requestFollow(this.userInfo.userNickname)
    },
    unfollow(){
      this.followState = 0;
      unfollow(this.userInfo.userNickname)
    }
  },
  data: function(){
    return{
      followState : -1
    }
  }

}
</script>