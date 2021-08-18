<template>
  <div id="app">
    <div class="background-color"></div>
    <div class="display">
      <router-view></router-view>
    </div>
    <NavBottom v-if="nav" :loc="loc"/>
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
    const loc = ref(0)

    if (!localStorage.getItem('Authorization') || localStorage.getItem('Authorization') == undefined) {
      router.push('/')
    }
    console.log(loc.value)
    if (route.path == '/newsfeed') {
      loc.value = 1
    } else if (route.path == '/search') {
      loc.value = 2
    } else if (route.path == '/write') {
      loc.value = 3
    } else if (route.path == '/alert') {
      loc.value = 4
    } else if (route.path == '/profile') {
      loc.value = 5
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