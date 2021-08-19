<template>
  <div class="newsfeed-card">
    <!-- <router-link :to="{ name: 'RouteArticle', params: { bid: article.boardResponse.bid } }"> -->
    <div>
      <div class="newsfeed-card-top">
        <div class="newsfeed-title">
          <router-link :to="{path : '/profile', query: {nickname : article.userDto.userNickname} }">
            <span class="newsfeed-writer">
              <div>
                <img :src="article.userDto.userImage" class="newsfeed-writerImage">
              </div>
              <div class="newsfeed-right">
                <div>
                  <span class="newsfeed-writerNickname">{{ article.userDto.userNickname }}</span>
                </div>
                <div>
                  <span class="newsfeed-type">장소</span>
                </div>
              </div>
            </span>
          </router-link>
          <div class="newsfeed-like">
            <span>{{ article.boardResponse.boardLike }}</span>
            <i class="fas fa-heart active"></i>
          </div>
        </div>
        <!-- <div style="text-align:center; font-weight:500; font-size:18px; ">
          <span>[동선] {{ article.boardResponse.boardTitle }}</span>
        </div> -->

      </div>
      <div @click="showArticle" class="show-article">
        <div class="newsfeed-image">
          <img v-if="article.visitResponse.visitImage1" :src="article.visitResponse.visitImage1" alt="map image" class="newsfeed-image">
          <img v-else src="@/assets/images/map.jpg" alt="map image" class="newsfeed-image">
        </div>

        <div class="newsfeed-description" style="display:flex; justify-content:space-between;">
          <span>
            {{ article.visitResponse.visitContent }}
          </span>
        </div>
      </div>
    </div>
    <!-- </router-link> -->
  </div>
</template>

<script>
import '../css/newsfeed/newsfeed-card.css'
import { useRouter } from 'vue-router'

export default {
  name: 'SearchPlaceCard',
    setup(props) {
    const router = useRouter()

    const showArticle = () => {
      router.push( {name: 'VisitArticle', query: { vid: props.article.visitResponse.vid }});
    }
    return { showArticle }
  },
  props: ['article']
}
</script>