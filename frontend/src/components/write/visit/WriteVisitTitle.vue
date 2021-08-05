<template>
  <div class = "write-route-title-box">
    <div class="write-visit-label"> 제목 </div>
    <input class = "write-input" type="text" v-model="title" @input="changeTitle">
  </div>
  
</template>

<script>
import "@/components/css/write/visit/writeVisitTitle.css"

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