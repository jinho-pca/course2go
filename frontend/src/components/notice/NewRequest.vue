<template>
  <div class="notice-category">
    <div class="notice-name">
      새로운 요청
    </div>
    <div class="notice-list">
      <div class="notice-item" v-for="(notice) in noticeList" :key="notice" @click="redirect(notice.uuid)">
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
          <button class="notice-btn notice-allow" @click="allow(notice.id)">
            수락
          </button>
          <div class="empty-space"></div>
          <button class="notice-btn notice-deny" @click="deny(notice.id)">
            거부
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {getNewRequest} from '@/compositions/notice/notice';
import {agreeFollow, denyFollow} from '@/compositions/follow/follow';
import "@/components/css/notice/noticeList.css"
export default {
  mounted() {
    this.initComponent();
    
  },
  methods:{
    initComponent(){
      getNewRequest().then(res =>{
        this.noticeList = res.object;
      })
    },
    redirect(){
      console.log("redirect");
    },
    allow(id){
      console.log(id);
      agreeFollow(id);
    },
    deny(id){
      console.log(id);
      denyFollow(id);
    }
  },
  data: function(){
    return{
      noticeList: [],
    }
  }
}
</script>
