<template>
  <div class="alert-list">
    <div class="new-alert">
      <div class="new-alert-title">
        새로운 요청
      </div>
      <AlertCard />
      <AlertCard />
      <AlertCard />
    </div>
    <div class="checked-alert">
      <div class="checked-alert-title">
        확인한 요청
      </div>
      <RequestCard />
      <RequestCard />
      <ReqeustCard />
    </div>
  </div>
</template>

<script>
import '@/components/css/alert/alert-list.css'
import AlertCard from '@/components/alert/AlertCard.vue'
import RequestCard from '@/components/alert/RequestCard.vue'
export default {
  name: 'AlertList',
  components: {
    AlertCard,
    RequestCard
  }
}
</script>

<style>

</style>