<template>
  <div style="height:100%;">
    <NavTop />
    <NewsfeedList />
  </div>
</template>

<script>
import NavTop from '@/components/navbar/NavTop.vue'
import NewsfeedList from '@/components/newsfeed/NewsfeedList.vue'
export default {
  name: 'Newsfeed',
  components: {
    NavTop,
    NewsfeedList
  }
}
</script>

<style>

</style>