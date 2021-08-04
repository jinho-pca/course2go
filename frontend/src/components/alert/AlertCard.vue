<template>
  <div class="alert-card">
    <div class="alert-message">
      <img src="@/assets/images/map.jpg" alt="user image">
      <span class="alert-name">안기훈</span>
      <span>님이 팔로우 요청을 했습니다</span>
    </div>
    <div class="alert-button">
      <button>수락</button>
      <button>거절</button>
    </div>
  </div>
</template>

<script>
import '@/components/css/alert/alert-card.css'
export default {
  name: 'AlertCard',
}
</script>