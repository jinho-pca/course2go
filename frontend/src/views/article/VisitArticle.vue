<template>
  <div class="route">
    <div class="route-tab" style="background:white;">
      <span @click="click">
        <i class="fas fa-chevron-left"></i>
      </span>
      <span>
        <span>장소 게시글</span>
      </span>
    </div>
      <visit-content> </visit-content>
      <visit-detail-card> </visit-detail-card>
      <Comment :vid="vid"/>
  </div>
</template>

<script>
import { readVisit } from '@/compositions/article/visit/read.js';
import { useRoute } from 'vue-router'
import VisitContent from '@/components/article/VisitContent.vue'
import VisitDetailCard from '@/components/article/VisitDetailCard.vue'
// import Comment from '@/components/article/Comment.vue'

export default {
  name: 'RouteArticle',
  setup() {
    const { articleVisit, read } = readVisit()
    const click = () => {
      history.back()
    }
    const route = useRoute()
    const vid = route.query.vid
    read(vid)

    return { click, articleVisit, read, vid }
  },
  components: {
    Comment,
    VisitDetailCard,
    VisitContent
  },
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