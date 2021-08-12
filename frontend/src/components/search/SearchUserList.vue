<template>
  <div class="search-user-card">
    <div class="card-left">
      <img :src="user.userImage" alt="user Image">
      <div>
        <div>
          {{ user.userName }}
        </div>
        <div>
          {{ user.userNickname }}
        </div>
      </div>
    </div>
    <div class="card-right">
      <div class="follow-button" v-if="user.followState == 1">언팔로우</div>
      <div class="follow-button" v-if="user.followState == 2">신청중</div>
      <div class="follow-button" v-else>팔로우</div>
    </div>
  </div>
</template>

<script>
import "@/components/css/search/search-user-list.css"

export default {
  name: 'SearchUserList',
  props: ['user']
}
</script>