<template>
  <div class = "write-visit-cost-box">
    <div class="write-visit-label"> 비용 </div>
    <input class = "write-input" type="text" v-model="title" @input="changeTitle">
  </div>
  <div class = "write-visit-time-box">
    <div class="write-visit-label"> 보낸 시간 </div>
    <input class = "write-input" type="text" v-model="title" @input="changeTitle">
  </div>
  <div class="write-visit-content">
		<div class="write-visit-label">내용</div>
		<textarea class="write-visit-content-textarea" rows="5" @input="changeRouteContent" v-model="routeContent"></textarea>
	</div>
</template>

<script>
import "@/components/css/write/visit/writeVisitContent.css"

export default {

}
</script>

<style>

</style>