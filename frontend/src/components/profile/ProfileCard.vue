<template>
  <div class="profile-card">
    <div class="profile-top">
      <div class="profile-imagebox">
        <img
          :src="profileData.userImage"
          alt="profile image"
          class="profile-image"
          v-if="profileData.userImage"
        />
        <img src="@/assets/images/profile.png" alt="profile image" class="profile-image" v-else />
      </div>
      <div class="profile-content">
        <div class="profile-namebox">
          <div class="profile-name">{{ profileData.userNickname }}</div>
          <!-- v-if(나){프로필 편집} v-elseif(팔로우){팔로우취소} v-else -->
          <div v-if="!nickname">
            <div class="profile-follow">
              <router-link :to="{ name: 'UpdateProfile', query: { profileData: profileData } }"
                >프로필 편집</router-link
              >
            </div>
            <div class="profile-follow" @click="logout">
              로그아웃
            </div>
          </div>

          <div
            class="profile-follow"
            v-if="
              (nickname && profileData.followState == 0 && followState == -1) || followState == 0
            "
            @click="follow"
          >
            <div>팔로우</div>
          </div>

          <div
            class="profile-follow"
            v-if="
              (nickname && profileData.followState == 1 && followState == -1) || followState == 1
            "
            @click="unfollow"
          >
            <div>언팔로우</div>
          </div>

          <div
            class="profile-follow"
            v-if="
              (nickname && profileData.followState == 2 && followState == -1) || followState == 2
            "
          >
            <div>신청중</div>
          </div>
        </div>
        <div class="profile-introduction">
          <span>{{ profileData.userComment }}</span>
        </div>
      </div>
    </div>
    <div class="profile-bottom">
      <div>
        <span class="tag">게시물</span>
        <span>{{ articleAmount }}</span>
      </div>
      <router-link :to="{ path: '/follow', query: { userNickname: profileData.userNickname, isfollowing: true } }">
        <div>
          <span class="tag">팔로잉</span>
          <span>{{ profileData.userFollowing }}</span>
        </div>
      </router-link>
      <router-link :to="{ path: '/follow', query: { userNickname: profileData.userNickname, isfollowing: false } }">
        <div>
          <span class="tag">팔로워</span>
          <span>{{ profileData.userFollower }}</span>
        </div>
      </router-link>
    </div>
  </div>
</template>

<script>
import "@/components/css/profile/profileCard.css";
import { requestFollow, unfollow } from "@/compositions/follow/follow";
import { userLogout } from "@/compositions/user/logout";

export default {
  name: "ProfileCard",
  props: {
    profileData: {
      type: Object,
    },
    nickname: {
      type: String,
    },
    articleAmount: {
      type: Number,
    }
  },
  methods: {
    follow() {
      this.followState = 2;
      requestFollow(this.profileData.userNickname);
    },
    unfollow() {
      this.followState = 0;
      unfollow(this.profileData.userNickname);
    },
  },
  data: function() {
    return {
      followState: -1,
    };
  },
  setup() {
    const { logout } = userLogout();
    return { logout };
  },
};
</script>
