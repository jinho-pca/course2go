<template>
  <div class="newsfeed-list">
    <div class="tab">
      <div class="select" @click="onClickSelect">
        <div class="text">전체</div>
        <span>▼</span>
        <ul class="option-list">
          <li class="option" value="1" @click="onClickOption">전체</li>
          <li class="option" value="2" @click="onClickOption">동선</li>
          <li class="option" value="3" @click="onClickOption">장소</li>
        </ul>
      </div> 
      <span>뉴스피드</span>
    </div>
    <div class="newsfeed-amount">총 {{ filteredNewsfeeds.length }} 개의 게시물이 있습니다.</div>
    <NewsfeedCard v-for="(newsfeed, idx) in filteredNewsfeeds" :key="idx" :newsfeed="newsfeed" @click="getArticle(newsfeed.board.bid)" />
  </div>
</template>

<script>
import '../css/newsfeed/newsfeed-list.css'
import NewsfeedCard from '@/components/newsfeed/NewsfeedCard.vue'
import { newsfeed } from '@/compositions/newsfeed';
import { readRoute } from '@/compositions/article/route/read.js';

export default {
  name: 'newsfeedlist',
  components: {
    NewsfeedCard
  },
  setup() {
    const { onClickSelect, onClickOption, getNewsfeed, newsfeeds, filteredNewsfeeds } = newsfeed();
    getNewsfeed();
    const getArticle = (bid) => {
      readRoute(bid)
    }
    return { onClickSelect, onClickOption, getNewsfeed, newsfeeds, filteredNewsfeeds, getArticle };
  },
}
</script>