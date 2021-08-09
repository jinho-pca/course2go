<template>
  <div class="notice-category">
    <div class="notice-name">
      새로운 요청
    </div>
    <div class="notice-list">
      <div class="notice-item" v-for="(notice, index) in noticeList" :key="notice">\
        <div>
          <div>
            {{notice.nickname}}님이 팔로우 요청을 했습니다.
          </div>
          <div class="notice-btns">
            <button class="notice-btn" @click="allow(index)">수락</button>
            <button class="notice-btn" @click="deny(index)">거절</button>
          </div>
        </div>
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
  methods:{
    deny(){
      // denyRequest()
    },
    allow(){
      // denyRequest()
    }

  },
  data: function(){
    return{
      noticeList: [],
    }
  }
}
</script>
