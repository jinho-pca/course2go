<template>
  <div class="search">
    <div class="search-position">
      <i class="fas fa-chevron-left"></i>
      <div class="search-tab">
        <div class="search-select" @click="onClickSelect">
          <div class="search-text" value="1" @click="getPage">동선</div>
          <span>▼</span>
          <ul class="search-option-list">
            <li class="option" value="1" @click="onClickOption">동선</li>
            <li class="option" value="2" @click="onClickOption">회원</li>
          </ul>
        </div>
        <span>검색</span>
      </div>
    </div>
    <SearchRoute v-if="page===1"/>
    <SearchUser v-else-if="page===2"/>
  </div>
</template>

<script>
import "@/assets/css/search.css"
import { search } from '@/compositions/search'

import SearchUser from '@/components/search/SearchUser.vue'
import SearchRoute from '@/components/search/SearchRoute.vue'
export default {
  name: 'Search',
  components: {
    SearchUser,
    SearchRoute
  },
  setup() {
    const { onClickSelect, onClickOption, page } = search();
    return { onClickSelect, onClickOption, page }
  }
}
</script>