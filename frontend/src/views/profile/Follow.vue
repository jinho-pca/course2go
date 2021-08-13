<template>
  <div class="follow">
    <div class="follow-tab">
      <i class="fas fa-chevron-left" @click="back"></i>
      
      <!-- <FollowSearch /> -->
      <!-- <div class="follow-button">
        <button style="border: 1px solid black; box-shadow: 2px 2px 2px grey;">팔로잉</button>
        <button>팔로워</button>
      </div> -->
      <FollowHeader @select="select"/>
    </div>
    <div v-show="!selectFollowing">
      <FollowCard v-for="(userInfo,index) in followingListData" :key ="index" :userInfo="userInfo"/>
    </div>
    <div v-show="selectFollowing">
      <FollowCard v-for="(userInfo,index) in followerListData" :key ="index" :userInfo="userInfo"/>
    </div>

  </div>
</template>

<script>
import '@/assets/css/profile/follow.css';
// import FollowSearch from '@/components/profile/FollowSearch.vue'
import FollowCard from '@/components/profile/FollowCard.vue'
import FollowHeader from '@/components/profile/FollowHeader.vue'
import {follow} from '@/compositions/profile/follow.js'
import {useRoute} from 'vue-router'

export default {
  name: 'Follow',
  components: {
    // FollowSearch,
    FollowCard,
    FollowHeader
  },
  
  setup() {
    const route = useRoute();
    
    const back = () => {
      history.back();
    }
    const {followerListData, followerList, followingList, followingListData} = follow();
    followerList(route.query.userNickname);
    followingList(route.query.userNickname);
    return { back, followerListData, followerList, followingList, followingListData }
  },
  methods:{
    select(select){
      if(select == "팔로잉"){
        this.selectFollowing = true;
      } else{
        this.selectFollowing = false;
      }
    }
  },
  data: function(){
    return{
      selectFollowing: true
    }
  }
}
</script>