<template>
  <div class="search">
    
    <i class="fas fa-chevron-left"></i>
    <div class="search-name">
      <span class="text">전체</span>
      <span>▼</span>
      <span>검색</span>
    </div>
    <div>
      <SearchInput />
      <SearchList />
    </div>
  </div>
</template>

<script>
import "@/assets/css/search.css"

import SearchInput from '@/components/search/SearchInput.vue'
import SearchList from '@/components/search/SearchList.vue'
export default {
  name: 'Search',
  components: {
    SearchInput,
    SearchList
  }
}
</script>