<template>
  <div id="comments">
    <h2>댓글</h2>
    <input class = "comment-input" type="text" v-model="mycomment" @input="changeTitle">
    <RouteCommentCard v-for="(comment, index) in commentlist" :key="index" :comment="comment"/>
  </div>
</template>
<script>
import {list} from '@/compositions/route/comment/list.js';
import RouteCommentCard from '@/components/article/RouteCommentCard.vue'
export default {
    // name: "RouteComment", 
    created() {
        // this.commentlist = list(5);
        console.log(list(5))
    },
    props: {
        bid:{
            type : number
        }
    },
    data() {
        return {
            mycomment: "",
            commentlist: [
                {
                    commentWriterUid: "구엑",
                    commentContent: "부엑1",
                    commentDepth: 0,
                },
                {
                    commentWriterUid: "구엑",
                    commentContent: "부엑2",
                    commentDepth: 1,
                },
                {
                    commentWriterUid: "구엑",
                    commentContent: "부엑3",
                    commentDepth: 0,
                },
            ],
        }
    },
    components:{
        RouteCommentCard
    },
}
</script>

<style>
#comments{
    text-align: left;
}
</style>