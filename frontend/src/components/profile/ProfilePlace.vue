<template>
  <div class="profile-place">
    <div class="place-namebox">
      <span class="place-name">
        장소 목록
      </span>
      <i class="far fa-plus">
        <!-- v-if -->
      </i>
    </div>
    <div>
      <!-- v-for -->
      <PlaceCard />
    </div>
  </div>
</template>

<script>
import "@/components/css/profile/profilePlace.css"
import PlaceCard from "@/components/profile/PlaceCard.vue"
export default {
  name: 'ProfilePlace',
  components: {
    PlaceCard
  }
}
</script>