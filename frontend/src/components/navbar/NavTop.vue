<template>
  <div class="nav-top">
    <div class="nav-items">
      <div class="nav-left">
        <router-link to="/newsfeed">
          <i class="fas fa-home"></i>
        </router-link>
      </div>
      <div class="nav-right">
        <!-- <span>
          <i class="fas fa-pen"></i>
        </span> -->
        <span>
          <i class="fas fa-unlock-alt" @click="logout"></i>
        </span>
      </div>
    </div>
  </div>
</template>

<script>
import "@/components/css/navbar/nav-top.css";
import { userLogout } from "@/compositions/user/logout";

export default {
  name: "navtop",
  setup() {
    const { logout } = userLogout();
    return { logout };
  },
};
</script>
