<template>
  <div class="place-card">
    <router-link :to="{ name: 'VisitArticle', query: { vid: place.visitResponse.vid }}">
      {{place.boardResponse.boardTitle}}
    </router-link>
  </div>
</template>

<script>
import "@/components/css/profile/placeCard.css"
export default {
  name: 'PlaceCard',
  props:  {
    place: {
      type: Object
    }
  },
}
</script>