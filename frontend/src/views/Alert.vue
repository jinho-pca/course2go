<template>
  <div class="alert">
    <div class="alert-tab">
      <!-- v-if -->
      <i class="fas fa-chevron-left"></i>
      <span>요청</span>
      <div class="alert-button">
        <button>알림</button>
        <button>요청</button>
      </div>
    </div>
    <div><!-- v-if -->
      <AlertList />
    </div>
  </div>
</template>

<script>
import '@/assets/css/alert.css';
import AlertList from '@/components/alert/AlertList.vue'
// import RequestList from '@/components/alert/RequestList.vue'
export default {
  name: 'Alert',
  components: {
    AlertList,
  }
}
</script>