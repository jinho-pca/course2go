<template>
  <div id="app">
    <div v-if="user">nav가 들어갈 자리</div>
    <router-view></router-view>
  </div>
</template>

<script>
import "./assets/css/style.css"
export default {
  name: "app"
}
</script>