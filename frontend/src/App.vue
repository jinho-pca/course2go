<template>
  <div id="app">
    <div class="background-color"></div>
    <div class="display">
      <router-view></router-view>
    </div>
    <NavBottom v-if="nav" :loc="loc" />
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
    const loc = ref(1)
    watchEffect(() => {
      if (route.path == '/' || route.path == '/signup' || route.path == '/email' || route.path == '/password') {
        nav.value = false
      } else {
        nav.value = true
      }
      
      if (route.path == '/newsfeed') {
        loc.value = 1
      } else if (route.path == '/search') {
        loc.value = 2
      } else if (route.path == '/write/route') {
        loc.value = 3
      } else if (route.path == '/notice') {
        loc.value = 4
      } else if (route.path == '/profile') {
        loc.value = 5
      }
    })
    return { nav, loc }
  }
}
</script>