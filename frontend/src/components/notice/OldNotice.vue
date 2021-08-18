<template>
  <div class="notice-category">
    <div class="notice-name">
      확인한 알림
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
        <div class="notice-right">
          {{getTimeStamp(notice.noticeTime)}}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {getOldNotice} from '@/compositions/notice/notice';
import "@/components/css/notice/noticeList.css"
export default {
  mounted() {
    this.initComponent();
    
  },
  methods:{
    initComponent(){
      getOldNotice().then(res =>{
        this.noticeList = res.object.reverse();
      })
    },
    getTimeStamp(time){
      var gap = new Date().getTime() - new Date(time).getTime();
        gap = gap /1000 /60;
        var text = "";
        let timestamp = "";
        if (gap<1) {
          timestamp = "방금전";
          return;
        } else if (gap<60) {
          text = "분 전"
        } else if (gap<1440) {
          text = "시간 전"
          gap = gap /60;
        } else{
          text = "일 전"
          gap = gap /1440;
        }
        gap = parseInt(gap);
        timestamp = gap + text;
        return timestamp
    }
    
  },
  data: function(){
    return{
      noticeList: [],
    }
  }
}
</script>
