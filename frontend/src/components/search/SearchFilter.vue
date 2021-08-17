<template>
  <details open class="search-filter">
    <summary><span class="filter-title">필터</span></summary>
    <hr>
    <div class="content">
      <div class="filter-namebox">
        <span class="filter-name" v-if="article==1" @click="clickArticle">게시물 종류</span>
        <span class="filter-name active" v-else-if="article==2" @click="clickArticle">동선</span>
        <span class="filter-name active" v-else-if="article==3" @click="clickArticle">장소</span>
      </div>
      <div class="filter-namebox">
        <span class="filter-name" v-if="comment==1" @click="clickComment">댓글</span>
        <span class="filter-name active" v-else-if="comment==2" @click="clickComment">댓글↑</span>
        <span class="filter-name active" v-else-if="comment==3" @click="clickComment">댓글↓</span>
      </div>
      <div class="filter-namebox">
        <span class="filter-name" v-if="like==1" @click="clickLike">좋아요</span>
        <span class="filter-name active" v-else-if="like==2" @click="clickLike">좋아요↑</span>
        <span class="filter-name active" v-else-if="like==3" @click="clickLike">좋아요↓</span>
      </div>
      <div class="filter-date">
        <div>
          <label for="start">시작날짜</label>
          <div>
            <input type="date" name="start" v-model="startDate">
          </div>
        </div>
        <div>
          <label for="end">끝날짜</label>
          <div>
            <input type="date" name="end" v-model="endDate">
          </div>
        </div>
      </div>
    </div>
    <div class="search-applybox">
      <button class="search-apply" @click="filterApply">적용</button>
    </div>
  </details>
</template>

<script>
import "@/components/css/search/search-filter.css"
import { ref } from 'vue'

export default {
  name: 'SearchFilter',
  setup(_, { emit }) {
    const article = ref(1)
    const comment = ref(1)
    const like = ref(1)
    const startDate = ref(new Date)
    const endDate = ref(new Date)

    const clickArticle = () => {
      article.value = article.value < 3 ? article.value+1 : 1
    }
    const clickComment = () => {
      comment.value = comment.value < 3 ? comment.value+1 : 1
    }
    const clickLike = () => {
      like.value = like.value < 3 ? like.value+1 : 1
    }
    const filterApply = () => {
      emit('getFilter', {
        'article': article,
        'comment': comment,
        'like': like,
        'startDate': startDate,
        'endDate': endDate
      })
    }

    return { article, comment, like, startDate, endDate, clickArticle, clickComment, clickLike, filterApply }
  }
}
</script>