<template>
  <div class="profile-route">
    <div class="route-namebox">
      <span class="route-name">
        동선 목록
      </span>
      <i class="far fa-plus">
        <!-- v-if -->
      </i>
    </div>
    <div>
      <!-- v-for -->
      <RouteCard />
    </div>
  </div>
</template>

<script>
import "@/components/css/profile/profileRoute.css"
import RouteCard from "@/components/profile/RouteCard.vue"
export default {
  name: 'ProfileRoute',
  components: {
    RouteCard
  }
}
</script>