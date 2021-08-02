<template>
  <div id="app">
    <div class="display">
      <router-view></router-view>
    </div>
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