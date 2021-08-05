<template>
  <div>
    <span v-if="comment.commentDepth>0">ㄴ</span>
    <span>{{comment.commentWriterNickName}}</span> <span>{{comment.commentContent}}</span>
  </div>
</template>

<script>
export default {
    name : "RouteCommentCard",
    props: {
        comment:{
            type : Object
        }
    }
}
</script>

<style>

</style>