<template>
  <router-link :to="{ path: '/routeArticle', data: { bid: route.boardResponse.bid }}">
    <div class="route-card">
      {{route.boardResponse.boardTitle}}
    </div>
  </router-link>
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
}
</script>