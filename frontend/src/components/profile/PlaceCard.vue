<template>
  <div class="place-card">
      {{place.boardResponse.boardTitle}}
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