<template>
  <div class="profile">
    <!-- v-if -->
    <div class="profile-page">
      <span v-if="!nickname" class="my-profile">내 프로필</span>
      <span v-if="nickname" class="my-profile">{{nickname}}의 프로필</span>
      
      <router-link to="/modify" v-if="!nickname">
        <i class="fas fa-cog"></i>
      </router-link>
    </div>
    <!-- <div class="profile-page">
      <span class="other-profile">
        타인 프로필
      </span>
    </div> -->
    <!-- endif -->
    <ProfileCard :profileData="profileData" :nickname="nickname" />
    <ProfileRoute :routeListData="routeListData" />
    <ProfilePlace :visitListData="visitListData" />
  </div>
</template>

<script>
import '@/assets/css/profile/profile.css';
import ProfileCard from '@/components/profile/ProfileCard.vue'
import ProfileRoute from '@/components/profile/ProfileRoute.vue'
import ProfilePlace from '@/components/profile/ProfilePlace.vue'
import { useRoute } from 'vue-router';
import { profile } from '@/compositions/profile.js';

export default {
  name: 'Profile',
  components: {
    ProfileCard,
    ProfileRoute,
    ProfilePlace,
  },
  setup() {
    const { myProfile, who, profileData, routeList, routeListData, visitList, visitListData } = profile();
      const route = useRoute();
      myProfile(route.query.nickname);
      routeList(route.query.nickname);
      visitList(route.query.nickname);
      
    return { myProfile, who, profileData, routeList, routeListData, visitList, visitListData }
  },
  mounted(){
    this.nickname = this.$route.query.nickname
  },

  data(){
    return{
      nickname : "",
    }
  }

}
</script>