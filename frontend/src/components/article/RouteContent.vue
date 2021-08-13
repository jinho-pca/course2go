<template>
<div>
  <div class="follow-card" style="border:none; background: white;">
    <div class="card-left">
      <img :src="routeReadResponse.userDto.userImage" alt="profile image" class="profile-image">
      <div>
        <div>
          {{routeReadResponse.userDto.userNickname}}
        </div>
      </div>
    </div>
    <div class="card-right">
      <i class="far fa-heart"></i>
      <span class="follow-d">{{routeReadResponse.boardResponse.boardLike}}</span>
    </div>
  </div>
  <div>
    <div style="font-weight:bold; margin: 20px 0">{{routeReadResponse.boardResponse.boardTitle}}</div>
    <div>{{routeReadResponse.routeResponse.routeContent}}</div>
  </div>
</div>
</template>

<script>
import '@/components/css/profile/follow-card.css';

export default {
  name: 'RouteContent',
  props:{
    routeReadResponse:{
      type: Object
    },
  },
}
</script>