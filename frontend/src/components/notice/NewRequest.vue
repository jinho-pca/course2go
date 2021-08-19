<template>
  <div class="notice-category" v-if="hasNewRequest">
    <div class="notice-name">
      <!-- v-if="notice-list" -->
      새로운 요청
    </div>
    <div class="notice-list">
      <div class="notice-item" v-for="(notice, index) in noticeList" :key="notice">
        <div class="notice-left">
          <span class="notice-nickname">
            {{notice.noticeFromUserNickname}}
          </span>
          <span class="notice-comment" v-if="notice.noticeType == 1">
            님의 팔로우 요청
          </span>
          <span class="notice-comment" v-if="notice.noticeType == 2">
            님이 팔로우를 수락하셨습니다.
          </span>
        </div>
        <div class="notice-right" v-if="notice.noticeType == 1">
          <button class="notice-btn notice-allow" @click="allow(notice.id, index)">
            수락
          </button>
          <div class="empty-space"></div>
          <button class="notice-btn notice-deny" @click="deny(notice.id, index)">
            거부
          </button>
        </div>
        <div class="notice-right" v-if="notice.noticeType == 2">
          {{getTimeStamp(notice.noticeTime)}}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {getNewRequest} from '@/compositions/notice/notice';
import {agreeFollow, denyFollow} from '@/compositions/follow/follow';
import {checkRequest} from '@/compositions/notice/notice';
import "@/components/css/notice/noticeList.css"
export default {
  mounted() {
    this.initComponent();
  },
  methods:{
    initComponent(){
      getNewRequest().then(res =>{
        this.noticeList = res.object.reverse();
        if (this.noticeList.length==0) {
          this.hasNewRequest=false
        }
        checkRequest();
      })
    },
    allow(id, index){
      console.log(id);
      agreeFollow(id);
      this.noticeList.splice(index, 1);
    },
    deny(id, index){
      console.log(id);
      denyFollow(id);
      this.noticeList.splice(index, 1);
    },
    getTimeStamp(time){
      var gap = new Date().getTime() - new Date(time).getTime()-32400000;
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
      hasNewRequest: true,
    }
  }
}
</script>
