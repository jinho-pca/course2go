<template>
  <div class = "write-visit-time-box">
    <div class="write-visit-label"> 장소 </div>
    <div class = "write-input" style="border: none">{{placeName}}</div>
    <!-- <input v-model="placeName" class = "write-input" type="text" disabled style="background: white; border: none"> -->
  </div>
</template>

<script>
import "@/components/css/write/visit/writeVisit.css"
export default {
	emits: ['pid'],
  mounted() {
		this.pid = this.$route.query.pid;
		this.placeName = this.$route.query.placeName;
		this.$emit('pid', this.pid);
	}, 
    
	data: function(){
		return{
			pid : "",
			placeName : ""
		}
	}
}
</script>

<style>

</style>