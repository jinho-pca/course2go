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
import { onMounted, watchEffect } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { profile } from '@/compositions/profile';

export default {
  name: 'navbottom',
  setup(props) {
    const router = useRouter()
    const route = useRoute()
    const { me, myProfile } = profile()
    onMounted(() => {
      const items = document.querySelectorAll('#nav-items div')
      for (let i=0; i < items.length; i++) {
        let item = document.querySelector(`#${items[i].id}`)
        item.className = ''
      }
      items[props.loc-1].className = 'active'
    })
    watchEffect(() => {
      console.log(props.loc)
      const items = document.querySelectorAll('#nav-items div')
      console.log(items.length)
      if (items.length) {
        for (let i=0; i < items.length; i++) {
          let item = document.querySelector(`#${items[i].id}`)
        item.className = '';
        }
        items[props.loc-1].className = 'active'
      }
    })
    const goProfile = async () => {
      if (route.query.nickname != me.userNickname || route.query.userNickname) {
        await router.push('/profile')
        await location.reload()
      }
    }
    myProfile()
    return { props, goProfile, me, myProfile }
  },
  props: ['loc']
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