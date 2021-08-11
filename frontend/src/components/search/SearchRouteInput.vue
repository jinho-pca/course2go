<template>
  <div class="search-input">
    <input type="text" name="search" id="search" v-model="word">
    <i class="fas fa-search" @click="getWord"></i>
  </div>
</template>

<script>
import "@/components/css/search/search-input.css"
import { ref } from 'vue'

export default {
  name: 'SearchInput',
  setup(_, { emit }) {
    const word = ref('')
    const getWord = () => {
      emit('getWord', word.value)
    }
    return { word, getWord }
  },
  emits: ['getWord'],
}
</script>