<template>
  <div class="route-article">
    <div class="route-article-tab">
      <span @click="click" class="back">
        <i class="fas fa-chevron-left"></i>
      </span>
      <span class="route-tab-title">
        <span>동선 게시글</span>
      </span>
    </div>
    <RouteContent :articleRoute="articleRoute"/>
    <RouteDetailCard :articleRoute="articleRoute"/>
    <Comment :bid="bid"/>
  </div>
</template>

<script>
import { readRoute } from '@/compositions/article/route/read.js';
import RouteDetailCard from '@/components/article/RouteDetailCard.vue'
import Comment from '@/components/article/Comment.vue'
import RouteContent from '@/components/article/RouteContent.vue'
import { useRoute } from 'vue-router'
// import { ref } from 'vue'

export default {
  name: 'RouteArticle',
  components: {
    RouteDetailCard,
    Comment,
    RouteContent,
  },
  setup() {
    const { articleRoute, read } = readRoute()
    const click = () => {
      history.back()
    }
    const route = useRoute()
    const bid = route.query.bid
    console.log(articleRoute)
    read(bid)
    return { click, articleRoute, read, bid }
  }
}
</script>

<style>
.route-article {
  padding: 0;
  margin: 50px 0 0 0;
}

.route-article-tab {
  text-align: center;
  position: fixed;
  top: 0;
  height: 7%;
  margin: -2px 0 0 0;
  padding: 10px 0 0 0;
  left: 50%;
  width: 100%;
  transform: translate(-50%, 0);
  background: white;
  z-index: 2;
}

.route-article-tab > span:first-child {
  position: fixed;
  left: 0;
  padding: 0 0 0 10px;
}

.route-article-tab > span:last-child {
  font-weight: bold;
  font-size: 19px;
  text-align: center;
}



</style>