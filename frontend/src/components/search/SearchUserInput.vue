<template>
  <div class="search-input">
    <input
      type="text"
      name="search"
      id="search"
      v-model="word"
      @enter="getWord"
      placeholder="이름 또는 닉네임을 입력해주세요"
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
    return { word, getWord };
  },
  emits: ["getWord"],
};
</script>
