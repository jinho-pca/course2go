<template>
  <div>
    <NavTop />
    <NewsfeedList @click="click"/>
  </div>
</template>

<script>
import NavTop from '@/components/navbar/NavTop.vue'
import NewsfeedList from '@/components/newsfeed/NewsfeedList.vue'
import { newsfeed } from '@/compositions/newsfeed'
import { read } from '@/compositions/route/read.js';

export default {
  name: 'Newsfeed',
  components: {
    NavTop,
    NewsfeedList
  },
  setup() {
    const { getNewsfeed } = newsfeed();
    const bid = 36
    const click = () => {
      read(bid)
    }
    getNewsfeed();
    return { getNewsfeed, click }
  }
}
</script>