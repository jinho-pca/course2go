<template>
  <div class="comment-card">
    <div class="comment-main" v-if="!isreply && !comment.commentDeleted">
      <div class="comment-writer-imagebox">
        <img :src="comment.commentWriterDto.userImage" alt="profile image" class="comment-image">
      </div>
      <div class="comment-content">
        <div class="comment-text">
          <span class="conmment-writer-nickname">{{comment.commentWriterDto.userNickname}}&nbsp;</span>
          <span>{{comment.commentContent}}</span> 
        </div>
        <div class="comment-footer">
          <span>{{timestamp}}</span>&nbsp;&nbsp;
          <!-- <span>좋아요 {{comment.commentLike}}</span>&nbsp;&nbsp; -->
          <span v-if="!writingreply" v-on:click="writingreply=true">답글</span>&nbsp;&nbsp;
          <span v-if="isMyComment()" v-on:click="deleteComment">삭제</span>
        </div>
      </div>
    </div>
    <div class="comment-deleted" v-if="comment.commentDeleted">
      <div class="comment-writer-imagebox" />
      <div class="comment-content">
      삭제된 메시지입니다.
      </div>
    </div>

    <div class="comment-reply-main" v-if="isreply">
      <div class="comment-writer-imagebox">
        <img :src="comment.commentWriterDto.userImage" alt="profile image" class="comment-image">
      </div>
      <div class="comment-content">
        <div class="comment-text">
          <span class="conmment-writer-nickname">{{comment.commentWriterDto.userNickname}}&nbsp;</span>
          <span>{{comment.commentContent}}</span> 
        </div>
        <div class="comment-footer">
          <span>{{timestamp}}</span>&nbsp;&nbsp;
          <!-- <span>좋아요 {{comment.commentLike}}</span>&nbsp;&nbsp; -->
          <span v-if="!writingreply" v-on:click="writingreply=true">답글</span>&nbsp;&nbsp;
          <span v-if="isMyComment()" v-on:click="deleteComment">삭제</span>
        </div>
      </div>
    </div>

    <div class="writingreply" v-if="writingreply">
      <CommentInput class="replying" :vid="vid" :bid="bid" :parent="replyParent" :direct="comment.cid" @close="close" @reload="reload"/>
    </div>
  </div>
</template>

<script>
import "@/components/css/article/comment/commentCard.css"
import CommentInput from '@/components/article/CommentInput.vue'
import { jwtdecoder } from '@/compositions/utils/jwtdecoder.js'
import { deleteComment } from '@/compositions/article/comment/delete.js';
export default {
  name : "CommentCard",
  props: {
    comment:{
      type : Object
    },
    bid:{
      type : String
    },
    vid:{
      type : String
    }
  },
  watch: {
    comment: {
      deep: true,
      handler(){
        this.update();
      }
    }
  },
  mounted() {
    this.setTimestamp();
    this.setReply();
    this.setReplyParent();
  },
  data() {
    return {
      writingreply: false,
      timestamp: "",
      isreply: false,
      replyParent: 0,
    }
  },
  components:{
      CommentInput
  },
  methods: {
    update(){
      this.setTimestamp();
      this.setReply();
      this.setReplyParent();
    },
    setTimestamp() {
      var gap = new Date().getTime() - new Date(this.comment.commentTime).getTime()-32400000;
      gap = gap /1000 /60;
      var text = "";
      if (gap<1) {
        this.timestamp = "방금전";
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
      this.timestamp = gap + text;
    },
    setReply(){
      if (this.comment.commentParent != -1) {
        this.isreply=true;
      }
    },
    setReplyParent(){
      if (this.comment.commentParent==-1) {
        this.replyParent = this.comment.cid;
      } else {
        this.replyParent = this.comment.commentParent;
      }
    },
    close(){
      this.writingreply=false;
    },
    reload(){
      this.$emit('reload');
      this.close();
      this.setTimestamp();
      this.setReply();
      this.setReplyParent();
    },
    isMyComment(){
      const { mynickname } = jwtdecoder();
      if (mynickname == this.comment.commentWriterDto.userNickname) {
        return true;
      }
      return false;
    },
    deleteComment(){
      deleteComment(this.comment.cid).then(() =>{
        this.$emit('reload')
      });
    }
  },
}
</script>

<style>

</style>