<template>
  <div class="follow-card">
    <div class="card-left">
      <img src="@/assets/images/나희승.png" alt="">
      <div>
        <div>
          {{userInfo}}
        </div>
        <div>
          Goki
        </div>
      </div>
    </div>
    <div class="card-right">
      <div class="follow-button">언팔로우</div>
    </div>
  </div>
</template>

<script>
import "@/components/css/profile/follow-card.css"

export default {
  name: 'FollowCard',
  props:{
    userInfo: {
      type: Object,
    }
  },

}
</script>