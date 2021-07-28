<template>
  <div id="app">
    <router-view></router-view>
    <NavBottom />
  </div>
</template>

<script>
import "./assets/css/style.css"
import NavBottom from '@/components/navbar/NavBottom.vue'
export default {
  name: "app",
  components: {
    NavBottom
  }
}
</script>