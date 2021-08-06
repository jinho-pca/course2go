<template>
  <div class="newsfeed-list">
    <div class="tab">
      <div class="select" @click="onClickSelect">
        <div class="text">전체</div>
        <span>▼</span>
        <ul class="option-list">
          <li class="option" @click="onClickOption">전체</li>
          <li class="option" @click="onClickOption">동선</li>
          <li class="option" @click="onClickOption">장소</li>
        </ul>
      </div>
      <span>뉴스피드</span>
    </div>
    <NewsfeedCard v-for="(newsfeed, idx) in newsfeeds" :key="idx" :newsfeed="newsfeed" />
  </div>
</template>

<script>
import '../css/newsfeed/newsfeed-list.css'
import NewsfeedCard from '@/components/newsfeed/NewsfeedCard.vue'
import { dropdown } from '@/compositions/dropdown';
import { newsfeed } from '@/compositions/newsfeed';

export default {
  name: 'newsfeedlist',
  components: {
    NewsfeedCard
  },
  setup() {
    const { onClickSelect, onClickOption } = dropdown();
    const { getNewsfeed, newsfeeds } = newsfeed();
    getNewsfeed();
    console.log(newsfeeds.value)
    return { onClickSelect, onClickOption, getNewsfeed, newsfeeds };
  },
}
</script>