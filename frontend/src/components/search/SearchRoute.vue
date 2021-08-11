<template>
  <div>
    <SearchRouteInput @getWord="getWord"/>
    <SearchRouteList />
    {{ getWord }}
  </div>
</template>

<script>
import SearchRouteInput from '@/components/search/SearchRouteInput.vue'
import SearchRouteList from '@/components/search/SearchRouteList.vue'

export default {
  name: 'SearchRoute',
  components: {
    SearchRouteInput,
    SearchRouteList,
  }
}
</script>