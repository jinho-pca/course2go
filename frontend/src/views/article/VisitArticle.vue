<template>
  <div class="route">
    <div class="route-tab" style="background:white;">
      <span @click="click">
        <i class="fas fa-chevron-left"></i>
      </span>
      <span>
        <span>장소 게시글</span>
      </span>
    </div>
      <!-- <RouteContent :routeReadResponse="routeReadResponse"/>
      <RouteDetailCard :routeReadResponse="routeReadResponse"/>
      <Comment :bid="bid"/> -->
  </div>
</template>

<script>
import { readVisit } from '@/compositions/article/route/read.js';
// import RouteDetailCard from '@/components/article/RouteDetailCard.vue'
// import Comment from '@/components/article/Comment.vue'
// import RouteContent from '@/components/article/RouteContent.vue'

export default {
  name: 'RouteArticle',
  created() {
    readVisit(this.bid).then(res => {
    this.routeReadResponse = res;
    console.log(this.routeReadResponse);
  });
  },
  components: {
    // RouteDetailCard,
    // Comment,
    // RouteContent,
  },
  data() {
    return {
      bid: parseInt(this.$route.params.bid),
      visitReadResponse: {},
    }
  },
  methods: {
    click() {
      history.back()
    }
  }
}
</script>

<style>
.route {
  padding: 0;
  margin: 50px 0 0 0;
}

.route-tab {
  text-align: start;
  position: fixed;
  top: 0;
  height: 7%;
  margin: -2px 0 0 0;
  padding: 10px 0 0 0;
  left: 50%;
  width: 100%;
  transform: translate(-50%, 0);
  background: white;
  z-index: 2;
}

.route-tab > span:first-child {
  padding: 0 115px 0 10px;
}

.route-tab > span:last-child {
  font-weight: bold;
  font-size: 19px;
}



</style>