<template>
  <div class="notice-category">
    <div class="notice-name">
      확인한 요청
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
      </div>
    </div>
  </div>
</template>

<script>
import {getOldRequest} from '@/compositions/notice/notice';
import {agreeFollow, denyFollow} from '@/compositions/follow/follow';
import "@/components/css/notice/noticeList.css"
export default {
  mounted() {
    this.initComponent();
    
  },
  methods:{
    initComponent(){
      getOldRequest().then(res =>{
        this.noticeList = res.object;
      })
    },
    redirect(){
      console.log("redirect");
    },
    allow(id, index){
      console.log(id);
      agreeFollow(id);
      this.noticeList.splice(index);
    },
    deny(id, index){
      console.log(id);
      denyFollow(id);
      this.noticeList.splice(index);
    }
  },
  data: function(){
    return{
      noticeList: [],
    }
  }
}
</script>
