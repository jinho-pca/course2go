<template>
  <div class="newsfeed-card">
    <!-- <router-link :to="{ name: 'RouteArticle', params: { bid: newsfeed.board.bid } }"> -->
    <div @click="showArticle">
      <div class="newsfeed-card-top">
        <div class="newsfeed-title">
          <span>{{ newsfeed.board.boardTitle }}</span>
          <span>
            <span>{{ newsfeed.board.boardLike }}</span>
            <i class="far fa-heart"></i>
            <i class="fas fa-heart active"></i>
          </span>
        </div>
        <div class="newsfeed-nickname">
          <router-link :to="{path : '/profile', query: {nickname : newsfeed.board.boardWriterNickname} }">
            <span>{{ newsfeed.board.boardWriterNickname }}</span>
          </router-link>
        </div>
      </div>
      
      <div class="newsfeed-image" v-if="newsfeed.board.boardType">
        <img  src="@/assets/images/map.jpg" alt="map image" class="newsfeed-image">
      </div>
      <div class="newsfeed-image" v-if="!newsfeed.board.boardType">
        <img :src="newsfeed.thing.visitImage1" alt="map image" class="newsfeed-image">
      </div>

      <div class="newsfeed-description">
        {{ newsfeed.thing.routeContent }}
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
        <div>
          <i class="fas fa-thumbs-up">{{ newsfeed.bestcomment.commentLike }}</i>
        </div>
      </div>
      <!-- comment -->
      <div class="comment-more">...더보기</div>
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
      console.log(props);
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