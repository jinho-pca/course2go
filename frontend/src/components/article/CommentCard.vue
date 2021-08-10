<template>
  <div>
    <span v-if="comment.commentDepth>0">ㄴ</span>
    <span>{{comment.commentWriterNickName}}</span> <span>{{comment.commentContent}}</span> <span v-if="!writingreply" v-on:click="writingreply=true">답글</span>
    <div v-if="writingreply">
      <CommentInput :parent="comment.cid"/>&nbsp;&nbsp;<i class="fas fa-times" v-on:click="writingreply=false"/>
    </div>
  </div>
</template>

<script>
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