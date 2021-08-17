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
      <img src="@/assets/images/map.jpg" alt="map image" class="newsfeed-image">
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
      if(props.newsfeed.board.boardType){
        router.push({name: 'RouteArticle', query: { bid: props.newsfeed.board.bid }});
      } else{
        console.log(props.newsfeed)
        router.push( {name: 'VisitArticle', query: { bid: props.newsfeed.board.bid }});
      }
    }
    return { showArticle }
  },
  props: ['newsfeed']
}
</script>