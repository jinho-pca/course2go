<template>
  <div class="comment-input">
    <div class="comment-input-main" v-if="!isreply">
      <div class="comment-input-writer-imagebox">
        <img :src="myimage" alt="profile image" class="profile-image">
      </div>
      <div class="comment-input-contents">
        <input class = "comment-input-content" type="text" v-model="mycomment" @input="changeTitle" placeholder="댓글 입력"/>
        <div class="comment-input-content-icons">
          <i class="fas fa-pen" v-on:click="writeComment(parent)"/>
        </div>
      </div>
    </div>

    <div class="comment-input-reply-main" v-if="isreply">
      <div class="comment-input-writer-imagebox">
        <img :src="myimage" alt="profile image" class="profile-image">
      </div>
      <div class="comment-input-contents">
        <input class = "comment-input-content" type="text" v-model="mycomment" @input="changeTitle" placeholder="댓글 입력"/>
        <div class="comment-input-content-icons">
          <i class="fas fa-pen" v-on:click="writeComment()"/>
          &nbsp;
          <i class="fas fa-times" v-on:click="cancelReply()"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { jwtdecoder } from '@/compositions/utils/jwtdecoder.js'
import "@/components/css/article/comment/commentInput.css"
import { write, writeByVid } from '@/compositions/article/comment/write.js'
export default {
    emits: ["close", "reload"],
    props: {
        parent:{
            type : Number
        },
        direct:{
            type : Number
        },
        bid:{
            type : String
        },
        vid:{
            type : String
        },
    },
    created() {
        const { myimage } = jwtdecoder();
        this.myimage = myimage;
        this.isReply()
    },
    data() {
        return {
            mycomment: "",
            myimage:"",
            isreply: false,
        }
    },
    methods: {
        writeComment() {
          if(this.bid){
            write(this.parent, this.mycomment, this.bid).then(() =>{
              this.$emit('reload')
              });
            } else {
              console.log(this.vid);
              writeByVid(this.parent, this.mycomment, this.vid).then(() =>{
                this.$emit('reload')
              });
            }
            this.mycomment = ''
        },
        isReply(){
            if (this.parent != -1) {
                this.isreply=true;
            }
        },
        cancelReply(){
            this.$emit('close');
        },
    },
}
</script>

<style>

</style>