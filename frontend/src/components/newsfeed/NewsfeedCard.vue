<template>
  <div class="newsfeed-card">
    <!-- <router-link :to="{ name: 'RouteArticle', params: { bid: newsfeed.board.bid } }"> -->
    <div>
      <div class="newsfeed-card-top">
        <div class="newsfeed-title">
          <router-link :to="{path : '/profile', query: {nickname : newsfeed.board.boardWriterNickname} }">
            <span class="newsfeed-writer">
              <div>
                <img :src="newsfeed.board.boardWriterImage" class="newsfeed-writerImage">
              </div>
              <div class="newsfeed-right">
                <div>
                  <span class="newsfeed-writerNickname">{{ newsfeed.board.boardWriterNickname }}</span>
                </div>
                <div>
                  <span class="newsfeed-type" v-if="newsfeed.board.boardType">동선</span>
                  <span class="newsfeed-type" v-else>장소</span>
                </div>
              </div>
            </span>
          </router-link>
          <div class="newsfeed-like">
            <span>{{ newsfeed.board.boardLike }}</span>
            <i class="fas fa-heart active"></i>
          </div>
        </div>
        <!-- <div style="text-align:center; font-weight:500; font-size:18px; ">
          <span>[동선] {{ newsfeed.board.boardTitle }}</span>
        </div> -->

      </div>
      <div @click="showArticle">
        <div class="newsfeed-image" v-if="newsfeed.board.boardType">
          <img v-if="newsfeed.thing.routeImage" :src="newsfeed.thing.routeImage" alt="map image" class="newsfeed-image">
          <img v-else src="@/assets/images/map.jpg" alt="map image" class="newsfeed-image">
        </div>
        <div class="newsfeed-image" v-if="!newsfeed.board.boardType">
          <img v-if="newsfeed.thing.visitImage1" :src="newsfeed.thing.visitImage1" alt="map image" class="newsfeed-image">
          <img v-else src="@/assets/images/map.jpg" alt="map image" class="newsfeed-image">
        </div>

        <div class="newsfeed-description" style="display:flex; justify-content:space-between;">
          <span v-if="newsfeed.thing.routeContent">
            {{ newsfeed.thing.routeContent }}
          </span>
          <span v-else>
            {{ newsfeed.thing.visitContent }}
          </span>
        </div>
        <!-- comment -->
        <div class="newsfeed-card-bottom" v-if="newsfeed.bestcomment">
          <div class="newsfeed-comment">
            <div class="comment-writer">
              {{ newsfeed.bestcomment.commentWriterDto.userName }}
            </div>
            <div class="comment-content">
              {{ newsfeed.bestcomment.commentContent }}
            </div>
          </div>
        </div>
        <!-- comment -->
        <div class="comment-more" v-if="newsfeed.bestcomment">...더보기</div>
      </div>
    </div>
    <!-- </router-link> -->
  </div>
</template>

<script>
import '../css/newsfeed/newsfeed-card.css'
import { useRouter } from 'vue-router'

export default {
  name: 'newsfeedcard',
  setup(props) {
    const router = useRouter()

    const showArticle = () => {
      if(props.newsfeed.board.boardType){
        router.push({name: 'RouteArticle', query: { bid: props.newsfeed.board.bid }});
      } else{
        router.push( {name: 'VisitArticle', query: { vid: props.newsfeed.thing.vid }});
      }
    }
    return { showArticle }
  },
  props: ['newsfeed']
}
</script>