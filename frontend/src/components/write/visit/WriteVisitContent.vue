<template>
  <div class = "write-visit-cost-box">
    <div class="write-visit-label"> 비용 </div>
    <input class = "write-input" type="number" v-model="visitCost" @input="changeVisitCost">
  </div>
  <div class = "write-visit-time-box">
    <div class="write-visit-label"> 보낸 시간 </div>
    <input class = "write-input" type="number" v-model="visitTime" @input="changeVisitTime">
  </div>
  <div class="write-visit-content">
		<div class="write-visit-label">내용</div>
		<textarea class="write-visit-content-textarea" rows="5" @input="changeVisitContent" v-model="visitContent"></textarea>
	</div>
</template>

<script>
import "@/components/css/write/visit/writeVisitContent.css"

export default {
	emits: ["visitContent", "visitTime", "visitCost"],


	methods: {
    changeVisitTime(){
      this.$emit('visitTime', this.visitTime);
    },
		changeVisitCost(){
      this.$emit('visitCost', this.visitCost);
    },
		changeVisitContent(){
      this.$emit('visitContent', this.visitContent);
    },
    
  },

  data: function(){
		return{
      visitTime : "",
			visitCost : "",
			visitContent : ""
		}
	}
}
</script>

<style>

</style>