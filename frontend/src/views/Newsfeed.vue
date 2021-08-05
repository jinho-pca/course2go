<template>
  <div>
    <NavTop />
    <NewsfeedList />
  </div>
</template>

<script>
import NavTop from '@/components/navbar/NavTop.vue'
import NewsfeedList from '@/components/newsfeed/NewsfeedList.vue'
import { newsfeed } from '@/compositions/newsfeed'

export default {
  name: 'Newsfeed',
  components: {
    NavTop,
    NewsfeedList
  },
  setup() {
    const { getNewsfeed } = newsfeed();

    getNewsfeed();
    
    return { getNewsfeed }
  }
}
</script>

<style>

</style>