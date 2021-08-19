<template>
  <div>
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