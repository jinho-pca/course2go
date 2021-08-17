<template>
  <div id="comments">
    <h2>댓글</h2>
    <CommentInput :bid="bid" :parent="nonreply" :direct="nonreply" @reload="reload"/>
    <CommentCard v-for="(comment, index) in commentlist" :key="index" :bid="bid" :comment="comment" @reload="reload"/>
  </div>
</template>
<script>
import { list, listByVid } from '@/compositions/article/comment/list.js';
import CommentCard from '@/components/article/CommentCard.vue'
import CommentInput from '@/components/article/CommentInput.vue'
export default {
    name: "Comment", 
    created() {
        // this.commentlist = list(5);
        console.log("코멘트 시작")
        if(this.bid){
            list(this.bid).then( res => {
                    this.commentlist = res;
                });	
        }
        if(this.vid){
            listByVid(this.vid).then( res => {
                    this.commentlist = res;
                });	
        }
        console.log()
    },
    props: {
        bid:{
            type : String
        },
        vid:{
            type : String
        }
    },
    data() {
        return {
            nonreply: -1,
            commentlist: [] ,
        }
    },
    components:{
        CommentCard,
        CommentInput
    },
    methods: {
        reload(){
            list(this.bid).then( res => {
                console.log(res)
				this.commentlist = res;
			});	
        }
    },
}
</script>

<style>
#comments{
    text-align: left;
}
</style>