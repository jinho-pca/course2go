<template>
  <div class="follow-header">
    <div class="follow-title">
      {{select}} 리스트
    </div>
    <div class="follow-buttons">
      <button class="follow-button" @click="showFollowing()" :style="selectFollowing()">
        팔로잉
      </button>
      <button class="follow-button" @click="showFollower()" :style="selectFollower()">
        팔로워
      </button>
    </div>
  </div>
</template>

<script>
import '@/components/css/profile/follow-header.css'
export default {
  emits:["select"]
  ,
  props:{
    isFollowing:{
      type: Boolean,
    }
  },
  updated() {
    this.select = this.isFollowing ? "팔로잉" : "팔로워"
  },
  methods:{
    showFollowing(){
      this.select = "팔로잉";
      this.$emit("select",this.select);
    },

    showFollower(){
      this.select = "팔로워";
      this.$emit("select",this.select);
    },

    selectFollowing(){
      if(this.select == "팔로잉"){
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

    selectFollower(){
      if(this.select == "팔로워"){
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
      select : "팔로잉",
    }
  }
}
</script>

<style>

</style>