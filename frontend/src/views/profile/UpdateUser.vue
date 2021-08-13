<template>
  <div id="signup-wrap">
    <div class="signup-html">
      <h3 id="tab-1" name="tab" class="tab">회원 정보 수정</h3>
      <div class="signup-form">
        <div class="sign-up-htm">
          <div class="group">
            <label for="email" class="label">이메일</label>
            <div class="input-border">
              <span class="input">{{ user.userEmail }}</span>
            </div>
          </div>
          <div class="group">
            <label for="password" class="label">비밀번호</label>
            <div class="input-border">
              <input
              v-model="password"
              v-bind:class="{error: error.password, complete: !error.password&&password.length!==0}"
              placeholder="비밀번호를 입력하세요"
              type="password" 
              id="password"
              name="password"
              class="input"
              autocapitalize="off"
              >
            </div>
            <div class="error-text" v-if="error.password">{{error.password}}</div>
          </div>
          <div class="group">
            <label for="passwordConfirm" class="label">비밀번호 확인</label>
            <div class="input-border">
              <input
              v-model="passwordConfirm"
              v-bind:class="{error: error.passwordConfirm, complete: !error.passwordConfirm&&passwordConfirm.length!==0}"
              placeholder="비밀번호를 재입력하세요"
              type="password"
              id="passwordConfirm"
              name="passwordConfirm"
              class="input"
              autocapitalize="off"
              >
            </div>
            <div class="error-text" v-if="error.passwordConfirm">{{error.passwordConfirm}}</div>
          </div>
          <div class="group">
            <label for="nickname" class="label">닉네임</label>
            <div class="input-border">
              <input
              v-model="nickname"
              v-bind:class="{error: error.nickname, complete: !error.nickname&&nickname.length!==0}"
              placeholder="닉네임을 입력하세요"
              type="text" 
              id="nickname"
              name="nickname"
              class="input"
              autocapitalize="off"
              >
              <input type="button" value="중복확인" class="check-button" @click="checkNickname">
            </div>
            <div class="error-text" v-if="error.nickname">{{error.nickname}}</div>
          </div>
          <div class="group align-buttons">
            <input type="submit" @click="update" value="확인" class="button">
            <router-link to="/profile">
              <input type="submit" value="취소" class="button">
            </router-link>
          </div>
          <div class="group">
            <input type="button" @click="deleteUser" value="회원 탈퇴" class="delete-button">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/user/update-user.css';
import { watchEffect } from 'vue';
import { userUpdate } from '@/compositions/user/update.js';
import { userDelete } from '@/compositions/user/update.js';

export default {
  name: 'UpdateUser',
  setup() {
    const { 
      email, password, passwordConfirm, nickname, 
      error, isSubmit, passwordSchema, update, checkUpdateForm,
      checkNickname, nicknameSchema, user
      } = userUpdate();
    const { deleteUser } = userDelete();
    /* 이메일과 비밀번호에 변화가 있을 때 watch */
    watchEffect(() => checkUpdateForm({ 
      nickname: nickname.value,
      password: password.value,
      passwordConfirm: passwordConfirm.value,
      })
    )
    console.log(user)
    return {
      email,
      password, 
      passwordConfirm,
      nickname,
      error,
      update,
      isSubmit,
      passwordSchema,
      checkNickname,
      nicknameSchema,
      checkUpdateForm,
      user,
      deleteUser
    };
  }
}
</script>