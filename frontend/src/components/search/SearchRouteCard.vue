<template>
  <div class="newsfeed-card">
    <router-link :to="{ path: '/routeArticle', data: { bid: 16 } }">
    <div class="newsfeed-card-top">
      <div class="newsfeed-title">
        <router-link to="/profile">
          <span>{{ article.userDto.userNickname }}</span>
        </router-link>
        <span>{{ article.boardResponse.boardTitle }}</span>
      </div>
      <i class="far fa-star">{{ article.boardResponse.boardLike }}</i>
    </div>
    <img src="@/assets/images/map.jpg" alt="map image" class="newsfeed-image">
    <div class="newsfeed-description">
      {{ article.routeResponse.routeContent }}
    </div>
    </router-link>
  </div>
</template>

<script>
import '../css/newsfeed/newsfeed-card.css'

export default {
  name: 'SearchRouteCard',
  props: ['article']
}
</script>