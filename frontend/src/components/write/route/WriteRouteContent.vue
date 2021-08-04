<template>
	<div>
		<div>내용</div>
		<textarea name="" id="" cols="30" rows="10" @input="changeRouteContent" v-model="routeContent"></textarea>
	</div>
</template>

<script>

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
