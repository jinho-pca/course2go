<template>
  <div class="notice">
    <notice-header @show="show"/>

    <div class="notice-list">
      <new-notice v-show="selectNotice"/>
      <old-notice v-show="selectNotice"/>
      <new-request v-show="!selectNotice"/>
      <old-request v-show="!selectNotice"/>
    </div>

  </div>
</template>

<script>
import NoticeHeader from '@/components/notice/NoticeHeader.vue'
import NewNotice from '@/components/notice/NewNotice.vue'
import OldNotice from '@/components/notice/OldNotice.vue'
import NewRequest from '@/components/notice/NewRequest.vue'
import OldRequest from '@/components/notice/OldRequest.vue'
import '@/components/css/notice/notice.css'
export default {
  components:{
    NoticeHeader,
    NewNotice,
    OldNotice,
    NewRequest,
    OldRequest
  },
  methods:{
    show(select){
      if(select == "알림"){
        this.selectNotice = true;
      } else{
        this.selectNotice = false;
      }
    }
  },
  data: function(){
    return{
      selectNotice : true,
    }
  }
}
</script>

