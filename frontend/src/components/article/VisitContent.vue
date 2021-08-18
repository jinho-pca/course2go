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
      <span class="follow-d">{{articleVisit.boardResponse.boardLike + likeChange}}</span>
      &nbsp;
      <i class="fas fa-heart" v-if="(articleVisit.boardResponse.boardILike && clicklike==0) || clicklike == 1" @click="unlike"></i>
      <i class="far fa-heart" v-if="(!articleVisit.boardResponse.boardILike && clicklike ==0) || clicklike == -1" @click="like"></i>
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
import {like, unlike} from '@/compositions/article/like.js'
// import { ref } from 'vue'

export default {
  name: 'VisitContent',
  // setup(props) {
    // const articleRoute = ref(props.routeReadResponse)
    // return { articleRoute }
  // },
  props: ['articleVisit'],
  methods:{
    like(){
      this.clicklike = 1;
      this.likeChange++;
      like(this.articleVisit.boardResponse.bid);
    },
    unlike(){
      this.clicklike = -1;
      this.likeChange--;
      unlike(this.articleVisit.boardResponse.bid);
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