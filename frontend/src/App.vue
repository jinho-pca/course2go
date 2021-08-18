<template>
  <div id="app">
    <div class="background-color"></div>
    <div class="display">
      <router-view></router-view>
    </div>
    <NavBottom v-if="nav"/>
  </div>
</template>

<script>
import "./assets/css/style.css"
import NavBottom from '@/components/navbar/NavBottom.vue'
import { useRoute, useRouter } from 'vue-router'
import { ref, watchEffect } from 'vue'

export default {
  name: "app",
  components: {
    NavBottom
  },
  setup() {
    const router = useRouter()
    const route = useRoute()

    if (!localStorage.getItem('Authorization') || localStorage.getItem('Authorization') == undefined) {
      router.push('/')
    }

    const nav = ref('')
    watchEffect(() => {
      if (route.path == '/' || route.path == '/signup' || route.path == '/email' || route.path == '/password') {
        nav.value = false
      } else {
        nav.value = true
      }
    })
    return { nav }
  }
}
</script>