<template>
  <div>
    <span>ㄴ</span>
    <span>{{comment.commentWriterUid}}</span> <span>{{comment.commentContent}}</span>
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