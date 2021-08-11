<template>
  <div class="comment-card">
    <!-- <span v-if="comment.commentDepth>0">ㄴ</span> -->
    <div class="comment-main">
      <div class="comment-writer-imagebox">
        <img :src="comment.commentWriterDto.userImage" alt="profile image" class="profile-image">
      </div>
      <div class="comment-content">
        <div class="comment-content">
          <span class="conmment-writer-nickname">{{comment.commentWriterDto.userNickname}}&nbsp;</span>
          <span>{{comment.commentContent}}</span> 
        </div>
        <div>
          <span v-if="!writingreply" v-on:click="writingreply=true">답글</span>
        </div>
      </div>
    </div>

    <div v-if="writingreply">
      <CommentInput :parent="comment.cid"/>&nbsp;&nbsp;<i class="fas fa-times" v-on:click="writingreply=false"/>
    </div>
  </div>
</template>

<script>
import "@/components/css/article/comment/commentCard.css"
import CommentInput from '@/components/article/CommentInput.vue'
export default {
    name : "CommentCard",
    props: {
        comment:{
            type : Object
        }
    },
    data() {
      return {
        writingreply: false,
      }
    },
    components:{
        CommentInput
    },
}
</script>

<style>

</style>