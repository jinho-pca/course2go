<template>
  <div>
    <div style="margin:0 0 0 5px;">
      <span v-show="articles.length" style="font-size:12px; color:grey;">{{ articles.length }}개의 게시물이 있습니다</span>
      <span v-show="!articles.length" style="font-size:12px; color:grey;">검색 결과가 없습니다</span>
    </div>
    <div v-for="(article, idx) in articles" :key="idx">
      <SearchRouteCard v-if="article.boardResponse.boardType" :article="article" />
      <SearchPlaceCard v-else :article="article" />
    </div>
  </div>
</template>

<script>
import SearchRouteCard from '@/components/search/SearchRouteCard.vue'
import SearchPlaceCard from '@/components/search/SearchPlaceCard.vue'

export default {
  name: 'SearchRouteList',
  components: {
    SearchRouteCard,
    SearchPlaceCard,
  },
  props: ['articles']
}
</script>