<template>
  <div class="newsfeed-list">
    <div class="tab">뉴스피드</div>
    <NewsfeedCard />
  </div>
</template>

<script>
import '../css/newsfeed/newsfeedlist.css'
import NewsfeedCard from '@/components/newsfeed/NewsfeedCard.vue'
export default {
  name: 'newsfeedlist',
  components: {
    NewsfeedCard
  }
}
</script>

<style>

</style>