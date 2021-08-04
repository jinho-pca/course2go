<template>
  <div class="follow">
    <div class="follow-tab">
      <i class="fas fa-chevron-left"></i>
      <span>username</span>
      <Search />
      <div class="follow-button">
        <button>팔로잉</button>
        <button>팔로워</button>
      </div>
    </div>
    <FollowCard />
    <FollowCard />
  </div>

</template>

<script>
import '@/assets/css/profile/follow.css';
import Search from '@/components/profile/FollowSearch.vue'
import FollowCard from '@/components/profile/FollowCard.vue'

export default {
  name: 'Follow',
  components: {
    Search,
    FollowCard
  }
}
</script>