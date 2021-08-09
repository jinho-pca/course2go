<template>
  <div class="nav-bottom" id="nav-bottom">
    <div class="nav-items">
      <router-link to="/newsfeed">
        <i class="far fa-home"></i>
      </router-link>
      <router-link to="/search">
        <i class="far fa-search"></i>
      </router-link>
      <router-link to="/write/route">
        <i class="far fa-plus"></i>
      </router-link>
      <i class="far fa-bell"></i>
      <router-link to="/profile">
        <i class="far fa-user"></i>
      </router-link>
    </div>
  </div>
</template>

<script>
import '@/components/css/navbar/nav-bottom.css'
// import { onMounted } from 'vue'

export default {
  name: 'navbottom',
  // setup() {
  //   onMounted(() => {
  //     const { token } = BASE_URL();
  //     if (!token) {
  //       const nav = document.querySelector('#nav-bottom')
  //       nav.style.display = 'hidden';
  //     } else {
  //       const nav = document.querySelector('#nav-bottom')
  //       nav.style.display = 'block';
  //     }
  //   })
  // }
}
</script>