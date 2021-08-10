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
          <span class="notice-comment" v-if="notice.noticeType == 4">
            님의 팔로우 요청
          </span>
        </div>
        <div class="notice-right">
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
import "@/components/css/notice/noticeList.css"
export default {
  mounted() {
    this.initComponent();
    
  },
  methods:{
    initComponent(){
      getNewRequest().then(res =>{
        this.noticeList = res.object;
        console.log(res.object);
      })
    },
    redirect(){

    },
    allow(id){
      console.log(id);
    },
    deny(id){
      console.log(id);
    }
  },
  data: function(){
    return{
      noticeList: [],
    }
  }
}
</script>
