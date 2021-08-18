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
      <PlaceCard v-for="(place,index) in visitListData" :key ="index" :place="place"/>
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
  },
  props: {
    visitListData: {
      type: Array
    }
  },
}
</script>