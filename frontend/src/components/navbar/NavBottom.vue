<template>
  <div class="nav-bottom">
    <div class="nav-items">
      <router-link to="/newsfeed">
        <i class="far fa-home"></i>
      </router-link>
      <i class="far fa-comment-alt"></i>
      <i class="far fa-plus"></i>
      <i class="far fa-map"></i>
      <router-link to="/profile">
        <i class="far fa-user"></i>
      </router-link>
    </div>
  </div>
</template>

<script>
import '@/components/css/navbar/navbottom.css'
export default {
  name: 'navbottom'
}
</script>

<style>

</style>