<template>
  <div class="route-card" @click="test">
    {{route.boardResponse.boardTitle}}
  </div>
</template>

<script>
import "@/components/css/profile/routeCard.css"
export default {
  name: 'RouteCard',
  props:  {
    route: {
      type: Object
    }
  },
  methods:{
    test(){
      console.log(this.route);
    }
  }
}
</script>