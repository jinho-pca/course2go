<template>
  <div id="comments">
    <h2>댓글</h2>
    <CommentInput :parent="nonreply"/>
    <CommentCard v-for="(comment, index) in commentlist" :key="index" :comment="comment"/>
  </div>
</template>
<script>
import { list } from '@/compositions/article/comment/list.js';
import CommentCard from '@/components/article/CommentCard.vue'
import CommentInput from '@/components/article/CommentInput.vue'
export default {
    name: "Comment", 
    created() {
        // this.commentlist = list(5);
        console.log("코멘트 시작")
        list(this.bid).then( res => {
				this.commentlist = res;
			});	
        console.log()
    },
    props: {
        bid:{
            type : Number
        }
    },
    data() {
        return {
            nonreply: -1,
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
        CommentCard,
        CommentInput
    },
}
</script>

<style>
#comments{
    text-align: left;
}
</style>