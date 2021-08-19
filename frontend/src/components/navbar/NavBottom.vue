<template>
  <div class="nav-bottom" id="nav-bottom">
    <div class="nav-items" id="nav-items">
      <div id="nav1">
        <router-link to="/newsfeed">
          <i class="far fa-home"></i>
        </router-link>
      </div>
      <div id="nav2">
        <router-link to="/search">
          <i class="far fa-search"></i>
        </router-link>
      </div>
      <div id="nav3">
        <router-link to="/write/route" class="link">
          <i class="far fa-plus"></i>
        </router-link>
      </div>
      <div id="nav4">
        <router-link to="/notice">
          <i class="far fa-bell"></i>
        </router-link>
      </div>
      <div id="nav5">
        <!-- <router-link to="/profile" :key="$route.fullPath"> -->
        <i class="far fa-user" @click="goProfile"></i>
        <!-- </router-link> -->
      </div>
    </div>
  </div>
</template>

<script>
import '@/components/css/navbar/nav-bottom.css'
import { onMounted, watchEffect, ref } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { profile } from '@/compositions/profile';

export default {
  name: 'navbottom',
  setup() {
    const router = useRouter()
    const route = useRoute()
    const { me, myProfile } = profile()

    const loc = ref(1)
    onMounted(() => {
      const items = document.querySelectorAll('#nav-items div')
      for (let i=0; i < items.length; i++) {
        let item = document.querySelector(`#${items[i].id}`)
        item.className = ''
      }
      if (loc.value) {
        items[loc.value-1].className = 'active'
      }
    })

    watchEffect(() => {
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
      } else {
        loc.value = 0
      }

      const items = document.querySelectorAll('#nav-items div')
      if (items.length) {
        for (let i=0; i < items.length; i++) {
          let item = document.querySelector(`#${items[i].id}`)
          item.className = '';
        }
        if (loc.value) {
          items[loc.value-1].className = 'active'
        }
      }

    })
    const goProfile = async () => {
      if (route.query.nickname != me.userNickname || route.query.userNickname) {
        await router.push('/profile')
        await location.reload()
      } else {
        await router.push('/profile')
      }
    }
    myProfile()
    return { goProfile, me, myProfile, loc }
  }
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