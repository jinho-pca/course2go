<template>
  <div class="notice-category">
    <div class="notice-name">
      오래된 요청
    </div>
    <div class="notice-list">
      <div class="notice-item" v-for="(notice, index) in noticeList" :key="notice">\
        {{notice}} <br> {{index}}
      </div>
    </div>
  </div>
</template>

<script>
// import {getNewNotice} from '@/compositions/notice/notice';
import "@/components/css/notice/notice.css"
export default {
  // mounted() {
  //   getNewNotice().then(res =>{
  //     console.log(res);
  //       this.noticeList = res;
  //     })
  // },
  
  data: function(){
    return{
      noticeList: [],
    }
  }
}
</script>
