<template>
<div>
  <div class="follow-card" style="border:none; background: white;">
    <div class="card-left">
      <img :src="articleVisit.userDto.userImage" alt="profile image" class="profile-image">
      <div>
        <div>
          {{articleVisit.userDto.userNickname}}
        </div>
      </div>
    </div>
    <div class="card-right">
      <span class="follow-d">{{articleVisit.boardResponse.boardLike}}</span>
      <i class="far fa-heart"></i>
    </div>
  </div>
  <div>
    <!-- <div style="font-weight:bold; margin: 20px 0">{{articleRoute.boardResponse.boardTitle}}</div> -->
    <!-- <div>{{articleRoute.routeResponse.routeContent}}</div> -->
  </div>
</div>
</template>

<script>
import '@/components/css/profile/follow-card.css';
// import { ref } from 'vue'

export default {
  name: 'VisitContent',
  // setup(props) {
    // const articleRoute = ref(props.routeReadResponse)
    // return { articleRoute }
  // },
  props: ['articleVisit']
}
</script>