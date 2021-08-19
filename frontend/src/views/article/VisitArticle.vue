<template>
  <div class="visit">
    <div class="visit-tab">
      <span @click="click">
        <i class="fas fa-chevron-left"></i>
      </span>
      <span>
        <span>장소 게시글</span>
      </span>
    </div>
    <visit-content :articleVisit="articleVisit" />
    <visit-detail-card :articleVisit="articleVisit"/> 
    <Comment :vid="vid"/>
  </div>
</template>

<script>
import { readVisit } from '@/compositions/article/visit/read.js';
import { useRoute } from 'vue-router'
import VisitContent from '@/components/article/VisitContent.vue'
import VisitDetailCard from '@/components/article/VisitDetailCard.vue'
import Comment from '@/components/article/Comment.vue'

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
.visit {
  padding: 0;
  margin: 50px 0 0 0;
}

.visit-tab {
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

.visit-tab > span:first-child {
  position: fixed;
  left: 0;
  padding: 0 0 0 10px;
}

.visit-tab > span:last-child {
  font-weight: bold;
  font-size: 19px;
  text-align: center;
}
</style>