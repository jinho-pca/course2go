<template>
  <div class="profile">
    <!-- v-if -->
    <div class="profile-page">
      <span class="my-profile">내 프로필</span>
      <router-link to="/modify">
        <i class="fas fa-cog"></i>
      </router-link>
    </div>
    <!-- <div class="profile-page">
      <span class="other-profile">
        타인 프로필
      </span>
    </div> -->
    <!-- endif -->
    <ProfileCard :profileData="profileData" />
    <ProfileRoute />
    <ProfilePlace />
  </div>
</template>

<script>
import '@/assets/css/profile/profile.css';
import ProfileCard from '@/components/profile/ProfileCard.vue'
import ProfileRoute from '@/components/profile/ProfileRoute.vue'
import ProfilePlace from '@/components/profile/ProfilePlace.vue'
import { profile } from '@/compositions/profile.js';

export default {
  name: 'Profile',
  components: {
    ProfileCard,
    ProfileRoute,
    ProfilePlace,
  },
  setup() {
    const { myProfile, who, profileData } = profile();
      myProfile();
    return { myProfile, who, profileData }
  }

}
</script>