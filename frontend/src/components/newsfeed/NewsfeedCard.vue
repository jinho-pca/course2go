<template>
  <div class="newsfeed-card">
    <router-link :to="{ name: 'RouteArticle', params: { bid: newsfeed.board.bid } }">
    <div class="newsfeed-card-top">
      <div class="newsfeed-title">
        <router-link :to="{path : '/profile', query:{nickname : newsfeed.board.boardWriterNickname} }">
          <span>{{ newsfeed.board.boardWriterNickname }}</span>
        </router-link>
        <span>{{ newsfeed.board.boardTitle }}</span>
      </div>
      <i class="far fa-star">{{ newsfeed.board.boardLike }}</i>
    </div>
    <img src="@/assets/images/map.jpg" alt="map image" class="newsfeed-image">
    <div class="newsfeed-description">
      {{ newsfeed.thing.routeContent }}
    </div>
    <!-- comment -->
    <div class="newsfeed-card-bottom" v-if="newsfeed.bestcomment">
      <div class="newsfeed-comment">
        <div class="comment-writer">
          {{ newsfeed.bestcomment.commentWriterNickname }}
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
    </router-link>
  </div>
</template>

<script>
import '../css/newsfeed/newsfeed-card.css'
export default {
  name: 'newsfeedcard',
  props: {
    newsfeed: {
      type: Object
    }
  }
}
</script>