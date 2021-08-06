<template>
  <div class="newsfeed-card">
    <router-link :to="{ path: '/routeArticle', data: { bid: 36 } }" style="text-decoration:none;color:black;">
    <div class="newsfeed-card-top">
      <div class="newsfeed-title">
        <router-link to="/profile">
          <span>{{ newsfeed.board.boardWriterNickname }}</span>
        </router-link>
        <span style="font-weight:500">{{ newsfeed.board.boardTitle }}</span>
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
    <div style="font-size:10px; margin: 5px 10px 10px 0;">...더보기</div>
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