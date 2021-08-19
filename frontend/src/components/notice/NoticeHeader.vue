<template>
  <div class="notice-header">
    <div class="notice-buttons">
      <button class="notice-button" @click="showNotice()" :style="selectNotice()">
        알림
      </button>
      <button class="notice-button" @click="showRequest()" :style="selectRequest()">
        요청
      </button>
    </div>
  </div>
</template>

<script>
import '@/components/css/notice/noticeHeader.css'
export default {
  emits:["show"]
  ,
  methods:{
    showNotice(){
      this.select = "알림";
      this.$emit("show",this.select);
    },

    showRequest(){
      this.select = "요청";
      this.$emit("show",this.select);
    },

    selectNotice(){
      if(this.select == "알림"){
        return{
          'font-weight': 'bold',
          border: 'solid',
        } 
      } else{
        return{
          background : 'white',
        }
      }
    },

    selectRequest(){
      if(this.select == "요청"){
        return{
          'font-weight': 'bold',
          border: 'solid'
        } 
      } else{
        return{
          background : 'white',
        }
      }
    }, 
  },

  data: function(){
    return{
      select : "알림",
    }
  }
}
</script>

<style>

</style>