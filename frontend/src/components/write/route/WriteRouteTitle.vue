<template>
  <div>
    <div> 제목 </div>
    <input type="text" v-model="title" @input="changeTitle" class = "write-route-title">
    
  </div>
  
</template>

<script>
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