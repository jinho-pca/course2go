<template>
  <div class="route">
    <div class="route-tab" style="background:white;">
      <span @click="click">
        <i class="fas fa-chevron-left"></i>
      </span>
      <span>
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
.route {
  padding: 0;
  margin: 50px 0 0 0;
}

.route-tab {
  text-align: start;
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

.route-tab > span:first-child {
  padding: 0 115px 0 10px;
}

.route-tab > span:last-child {
  font-weight: bold;
  font-size: 19px;
}



</style>