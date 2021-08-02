<template>
  <div class="profile">
    <!-- v-if -->
    <div class="profile-page">
      <span class="tab">내 프로필</span>
      <i class="fas fa-cog"></i>
    </div>
    <div>
      타인 프로필
    </div>
    <!-- endif -->
    <ProfileCard />
    <ProfileRoute />
    <ProfilePlace />
  </div>
</template>

<script>
import '@/assets/css/profile.css';
import ProfileCard from '@/components/profile/ProfileCard.vue'
import ProfileRoute from '@/components/profile/ProfileRoute.vue'
import ProfilePlace from '@/components/profile/ProfilePlace.vue'
export default {
  name: 'Profile',
  components: {
    ProfileCard,
    ProfileRoute,
    ProfilePlace,
  }
}
</script>

<style>

</style>