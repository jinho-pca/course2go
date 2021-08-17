<template>
  <div>
    <SearchRouteCard v-for="(article, idx) in articles" :key="idx" :article="article" />
  </div>
</template>

<script>
import SearchRouteCard from '@/components/search/SearchRouteCard.vue'

export default {
  name: 'SearchRouteList',
  components: {
    SearchRouteCard
  },
  props: ['articles']
}
</script>