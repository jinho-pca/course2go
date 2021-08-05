<template>
  <div class = "write-route-title-box">
    <div class="write-route-title"> 제목 </div>
    <input class = "write-route-title-input" type="text" v-model="title" @input="changeTitle">
  </div>
  
</template>

<script>
import "@/components/css/write/route/writeRouteTitle.css"


export default {
  emits: ["title"],

  methods: {
    changeTitle(){
      this.$emit('title', this.title);
    },
    
  },

  data: function(){
		return{
      title : "",
		}
	}
}
</script>

<style>

</style>