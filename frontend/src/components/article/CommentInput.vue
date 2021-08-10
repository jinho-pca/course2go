<template>
<span>
  <input class = "comment-input-comment-content" type="text" v-model="mycomment" @input="changeTitle"/>
  &nbsp;
  <i class="fas fa-pen" v-on:click="writeComment(parent)"/>
</span>
</template>

<script>
import "@/components/css/article/comment/commentInput.css"
import { write } from '@/compositions/article/comment/write.js';
export default {
    props: {
        parent:{
            type : Number
        }
    },
    data() {
        return {
            mycomment: "",
        }
    },
    methods: {
        writeComment(parent) {
            write(parent, this.mycomment, this.bid);
        }
    },
}
</script>

<style>

</style>