<template>
  <div class="follow">
    <div class="follow-tab">
      <i class="fas fa-chevron-left" @click="back"></i>
      
      <!-- <FollowSearch /> -->
      <!-- <div class="follow-button">
        <button style="border: 1px solid black; box-shadow: 2px 2px 2px grey;">팔로잉</button>
        <button>팔로워</button>
      </div> -->
      <FollowHeader/>
    </div>
    <div>
      <FollowCard v-for="(userInfo,index) in followerListData" :key ="index" :userInfo="userInfo"/>
    </div>
  </div>

</template>

<script>
import '@/assets/css/profile/follow.css';
// import FollowSearch from '@/components/profile/FollowSearch.vue'
import FollowCard from '@/components/profile/FollowCard.vue'
import FollowHeader from '@/components/profile/FollowHeader.vue'

export default {
  name: 'Follow',
  components: {
    // FollowSearch,
    FollowCard,
    FollowHeader
  },
  setup() {
    const back = () => {
      history.back();
    }
    return { back }
  }
}
</script>