<template>
<div>
  <div class="article-content-user" style="border:none; background: white;">
    <div class="card-left">
        <img :src="articleRoute.userDto.userImage" alt="profile image" class="profile-image">
      <div class="user-nickname">
        &nbsp; {{articleRoute.userDto.userNickname}}
      </div>
    </div>
    <div class="card-right">
      <span class="follow-d">{{articleRoute.boardResponse.boardLike}}</span>
      <i class="far fa-heart"></i>
    </div>
  </div>
  <div>
    <div style="font-weight:bold; margin: 20px 0">{{articleRoute.boardResponse.boardTitle}}</div>
    <div>{{articleRoute.routeResponse.routeContent}}</div>
  </div>
</div>
</template>

<script>
import '@/components/css/article/article-content.css';
// import { ref } from 'vue'

export default {
  name: 'RouteContent',
  // setup(props) {
    // const articleRoute = ref(props.routeReadResponse)
    // return { articleRoute }
  // },
  props: ['articleRoute']
}
</script>