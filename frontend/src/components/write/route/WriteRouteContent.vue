<template>
	<div class="write-route-content">
		<div class="write-route-content-label">내용</div>
		<textarea class="write-route-content-textarea" rows="5" @input="changeRouteContent" v-model="routeContent"></textarea>
	</div>
</template>

<script>
import "@/components/css/write/route/writeRouteContent.css"
export default {
	

	emits: ["routeContent"],

	methods: {
		changeRouteContent(){
			this.$emit('routeContent', this.routeContent);
		},
	},
  
  data: function(){
		return{
			routeContent : "",
		}
	}
}
</script>
