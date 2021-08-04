<template>
  <div class="request-list">
    <div class="new-request">
      <div class="new-alert-title">
        새로운 요청
      </div>
      <RequestCard />
    </div>
    <div class="checked-request">
      <div class="checked-request-title">
        확인한 요청
      </div>
      <RequestCard />
    </div>
  </div>
</template>

<script>
import '@/components/css/alert/request-list.css'
import RequestCard from '@/components/alert/RequestCard.vue'
export default {
  name: 'RequestList',
  components: {
    RequestCard,
  }
}
</script>

<style>

</style>