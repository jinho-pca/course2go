<template>
  <div class="search-user-card">
    <router-link :to="{path : '/profile', query: {nickname : user.userNickname} }">
      <div class="card-left">
        <img :src="user.userImage" alt="user Image">
        <div>
          <div>
            {{ user.userName }}
          </div>
          <div>
            {{ user.userNickname }}
          </div>
        </div>
      </div>
    </router-link>
    <div class="card-right">
      <div class="follow-button" v-if="(user.followState == 0 && followState == -1) || followState == 0" @click="follow">
        <div>팔로우</div>
      </div>
      
      <div class="follow-button" v-if="(user.followState == 1 && followState == -1) || followState == 1" @click="unfollow">
        <div>언팔로우</div>
      </div>

      <div class="follow-button" v-if="(user.followState == 2 && followState == -1) || followState == 2">
        <div>신청중</div>
      </div>
    </div>
  </div>
</template>

<script>
import "@/components/css/search/search-user-list.css"
import {requestFollow, unfollow} from "@/compositions/follow/follow"
export default {
  name: 'SearchUserList',
  props: ['user'],
  data: function(){
    return{
      followState : -1
    }
  },
  methods:{
    follow(){
      this.followState = 2;
      requestFollow(this.user.userNickname)
    },
    unfollow(){
      this.followState = 0;
      unfollow(this.user.userNickname)
    }
  },
}
</script>