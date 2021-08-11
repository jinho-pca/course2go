<template>
  <div class="follow">
    <div class="follow-tab">
      <i class="fas fa-chevron-left" @click="back"></i>
      <span>팔로우 리스트</span>
      <FollowSearch />
      <div class="follow-button">
        <button style="border: 1px solid black; box-shadow: 2px 2px 2px grey;">팔로잉</button>
        <button>팔로워</button>
      </div>
    </div>
    <FollowCard />
  </div>

</template>

<script>
import '@/assets/css/profile/follow.css';
import FollowSearch from '@/components/profile/FollowSearch.vue'
import FollowCard from '@/components/profile/FollowCard.vue'

export default {
  name: 'Follow',
  components: {
    FollowSearch,
    FollowCard
  },
  setup() {
    const back = () => {
      history.back();
    }
    return { back }
  }
}
</script>