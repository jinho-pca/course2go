<template>
  <div class="notice-category">
    <div class="notice-name">
      오래된 요청
    </div>
    <div class="notice-list">
      <div class="notice-item" v-for="(notice) in noticeList" :key="notice" @click="redirect(notice.uuid)">
        <div class="notice-left">
          <span class="notice-nickname">
            {{notice.noticeFromUserNickname}}
          </span>
          <span class="notice-comment" v-if="notice.noticeType == 4">
            님의 팔로우 요청
          </span>
        </div>
        <div class="notice-right">
          <button class="notice-btn notice-allow">
            수락
          </button>
          <div class="empty-space"></div>
          <button class="notice-btn notice-deny">
            거부
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {getOldRequest} from '@/compositions/notice/notice';
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

    }    
  },
  data: function(){
    return{
      noticeList: [],
    }
  }
}
</script>
