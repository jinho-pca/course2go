<template>
  <div class="search-input">
    <input
      type="text"
      name="search"
      id="search"
      v-model="word"
      placeholder="이름 또는 닉네임을 입력해주세요"
      @keyup="getWordInput"
    />
    <i class="fas fa-search" @click="getWord"></i>
  </div>
</template>

<script>
import "@/components/css/search/search-input.css";
import { ref } from "vue";

export default {
  name: "SearchInput",
  setup(_, { emit }) {
    const word = ref("");
    const getWord = () => {
      if (word.value) {
        emit("getWord", word.value);
      } else {
        alert("회원 이름을 입력해주세요");
      }
    };
    const getWordInput = () => {
      if (word.value) {
        emit("getWord", word.value);
      }
    }
    return { word, getWord, getWordInput };
  },
  emits: ["getWord"],
};
</script>
