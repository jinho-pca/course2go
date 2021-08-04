<template>
<div class="overlaybox">
  <div class="map-popup-title">
    <h3 class="popup-name">공학관점</h3>
    <div class="congestion">
      <span class="congestion-text">바쁨</span>
    </div>
  </div>
  <span class="store-location">아산공학관 1층(110호)</span>
  <div class="popup-buttons">
    <div>
      <button class="popup-button" type="button">취소</button>
    </div>
    <div>
      <button class="popup-button" type="button">선택</button>
    </div>
  </div>
</div>
</template>

<script>
export default {
    data() {
        return {
            name: "동방파제",
            address: "제주특별자치도 서귀포시 서귀동 758-2",
        }
    },
}
</script>

<style>

</style>