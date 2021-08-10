<template>
  <div class="notice-category">
    <div class="notice-name">
      새로운 알림
    </div>
    <div class="notice-list">
      <div class="notice-item" v-for="(notice) in noticeList" :key="notice" @click="redirect()">
        <div class="notice-left">
          <span class="notice-nickname">
              {{notice.noticeFromUserNickname}}
          </span>
          <span class="notice-comment" v-if="notice.noticeType == 4">
            님이 댓글을 남기셨습니다.
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {getNewNotice} from '@/compositions/notice/notice';
import "@/components/css/notice/noticeList.css"
export default {
  mounted() {
    this.initComponent();
    
  },
  methods:{
    initComponent(){
      getNewNotice().then(res =>{
        console.log(res.object[0]);
        this.noticeList = res.object;
      })
      
    },
  },
  data: function(){
    return{
      noticeList: [],
    }
  }
}
</script>
