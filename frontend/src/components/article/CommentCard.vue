<template>
  <div class="comment-card">
    <div class="comment-main" v-if="!isreply">
      <div class="comment-writer-imagebox">
        <img :src="comment.commentWriterDto.userImage" alt="profile image" class="profile-image">
      </div>
      <div class="comment-content">
        <div class="comment-text">
          <span class="conmment-writer-nickname">{{comment.commentWriterDto.userNickname}}&nbsp;</span>
          <span>{{comment.commentContent}}</span> 
        </div>
        <div class="comment-footer">
          <span>{{timestamp}}</span>&nbsp;&nbsp;
          <span>좋아요 {{comment.commentLike}}</span>&nbsp;&nbsp;
          <span v-if="!writingreply" v-on:click="writingreply=true">답글</span>
        </div>
      </div>
    </div>

    <div class="comment-reply-main" v-if="isreply">
      <div class="comment-writer-imagebox">
        <img :src="comment.commentWriterDto.userImage" alt="profile image" class="profile-image">
      </div>
      <div class="comment-content">
        <div class="comment-text">
          <span class="conmment-writer-nickname">{{comment.commentWriterDto.userNickname}}&nbsp;</span>
          <span>{{comment.commentContent}}</span> 
        </div>
        <div class="comment-footer">
          <span>{{timestamp}}</span>&nbsp;&nbsp;
          <span>좋아요 {{comment.commentLike}}</span>&nbsp;&nbsp;
          <span v-if="!writingreply" v-on:click="writingreply=true">답글</span>
        </div>
      </div>
    </div>

    <div class="writingreply" v-if="writingreply">
      <CommentInput class="replying" :bid="bid" :parent="replyParent" :direct="comment.cid" @close="close" @reload="reload"/>
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
        },
        bid:{
            type : Number
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
      setTimestamp() {
        var gap = new Date().getTime() - new Date(this.comment.commentTime).getTime();
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
        this.emitting().then(()=>{
            this.close();
            this.setTimestamp();
            this.setReply();
            this.setReplyParent();
          });
      },
      emitting(){
        this.$emit('reload')
      }
    },
}
</script>

<style>

</style>