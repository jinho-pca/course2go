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
      <span class="follow-d">{{articleRoute.boardResponse.boardLike + likeChange}}</span>
      &nbsp;
      <i class="fas fa-heart" v-if="(articleRoute.boardResponse.boardILike && clicklike==0) || clicklike == 1" @click="unlike"></i>
      <i class="far fa-heart" v-if="(!articleRoute.boardResponse.boardILike && clicklike ==0) || clicklike == -1" @click="like"></i>
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
import {like, unlike} from '@/compositions/article/like.js'
// import { ref } from 'vue'

export default {
  name: 'RouteContent',
  // setup(props) {
    // const articleRoute = ref(props.routeReadResponse)
    // return { articleRoute }
  // },
  props: ['articleRoute'],
  methods:{
    like(){
      this.clicklike = 1;
      this.likeChange++;
      like(this.articleRoute.boardResponse.bid);
    },
    unlike(){
      this.clicklike = -1;
      this.likeChange--;
      unlike(this.articleRoute.boardResponse.bid);
    }
  },
  data: function(){
    return{
      clicklike : 0,
      likeChange : 0
    }
  }

}
</script>