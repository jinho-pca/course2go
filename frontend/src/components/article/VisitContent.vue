<template>
  <div>
    <div class="article-content-user" style="border:none; background: white;">
      <div class="card-left">
        <div>
          <img :src="articleVisit.userDto.userImage" alt="profile image" class="profile-image" />
        </div>
        <div class="user-nickname">&nbsp; {{ articleVisit.userDto.userNickname }}</div>
      </div>
      <div class="card-right">
        <span class="follow-d">{{ articleVisit.boardResponse.boardLike + likeChange }}</span>
        &nbsp;
        <i
          class="fas fa-heart"
          v-if="(articleVisit.boardResponse.boardILike && clicklike == 0) || clicklike == 1"
          @click="unlike"
        ></i>
        <i
          class="far fa-heart"
          v-if="(!articleVisit.boardResponse.boardILike && clicklike == 0) || clicklike == -1"
          @click="like"
        ></i>
      </div>
    </div>

    <!-- <div style="font-weight:bold; margin: 20px 0">{{articleVisit.boardResponse.boardTitle}}</div>
    <div>{{articleVisit.visitResponse.visitContent}}</div> -->

    <div class="article-content">
      <div class="article-title">
        <div>
          {{ articleVisit.boardResponse.boardTitle }}
        </div>
        <div>
          <button
            v-if="myNickname == articleVisit.userDto.userNickname"
            class="article-delete-btn"
            @click="deleteBoard"
          >
            삭제
          </button>
        </div>
      </div>
      <div class="article-content-text">
        {{ articleVisit.visitResponse.visitContent }}
      </div>
    </div>
  </div>
</template>

<script>
import "@/components/css/article/article-content.css";
import { like, unlike } from "@/compositions/article/like.js";
import { deleteBoard } from "@/compositions/article/delete.js";
import jwt from "jsonwebtoken";
import { BASE_URL } from "@/compositions/global.js";
// import { ref } from 'vue'

export default {
  name: "VisitContent",
  // setup(props) {
  // const articleRoute = ref(props.routeReadResponse)
  // return { articleRoute }
  // },
  props: ["articleVisit"],
  methods: {
    like() {
      this.clicklike = 1;
      this.likeChange++;
      like(this.articleVisit.boardResponse.bid);
    },
    unlike() {
      this.clicklike = -1;
      this.likeChange--;
      unlike(this.articleVisit.boardResponse.bid);
    },
    async deleteBoard(){
      await deleteBoard(this.articleVisit.boardResponse.bid);
      this.$router.push({name: 'Newsfeed'});
    }
    
  },
  data: function() {
    return {
      clicklike: 0,
      likeChange: 0,
      myNickname: jwt.decode(BASE_URL().token.substr(7)).userNickname,
    };
  },
};
</script>
