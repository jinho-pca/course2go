<template>
  <div>
    <SearchUserInput @getWord="getWord"/>
    <SearchUserList v-for="(user, idx) in users" :key="idx" :user="user" />
  </div>
</template>

<script>
import SearchUserInput from '@/components/search/SearchUserInput.vue'
import SearchUserList from '@/components/search/SearchUserList.vue'
import { search } from '@/compositions/search.js'

export default {
  name: 'SearchUser',
  components: {
    SearchUserInput,
    SearchUserList
  },
  setup() {
    const { searchUser, users } = search()
    async function getWord(word) {
      searchUser(word)
    }
    return { getWord, users }
  }
}
</script>