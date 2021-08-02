<template>
  <div id="signup-wrap">
    <div class="signup-html">
      <h3 id="tab-1" name="tab" class="tab">비밀번호 찾기</h3>
      <div class="signup-form">
        <div class="sign-up-htm">
          <div class="group">
            <label for="email" class="label">이메일</label>
            <div class="input-border">
              <input
              v-model="email"
              v-bind:class="{error: error.email, complete: !error.email&&email.length!==0}"
              placeholder="이메일을 입력하세요"
              type="text" 
              id="email"
              name="email"
              class="input"
              autocapitalize="off"
              >
            </div>
            <div class="error-text" v-if="error.email">{{error.email}}</div>
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
            </div>
            <div class="error-text" v-if="error.nickname">{{error.nickname}}</div>
          </div>
          <div class="group">
            <label for="birth" class="label">생년월일</label>
            <div class="input-border">
              <input
              v-model="birth"
              v-bind:class="{error: error.birth, complete: !error.birth&&birth.length!==0}"
              type="date" 
              id="birth"
              name="birth"
              class="input"
              autocapitalize="off"
              >
            </div>
            <div class="error-text" v-if="error.birth">{{error.birth}}</div>
          </div>
          <div class="group align-buttons">
            <input type="submit" @click="signup" value="확인" class="button">
            <router-link to="/">
              <input type="submit" value="취소" class="button">
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/user/signup.css';
import { watchEffect } from 'vue';
import { userPassword } from '@/compositions/user';

export default {
  name: 'Password',
  setup() {
    const { email, nickname, birth, error, checkPasswordForm, findPassword } = userPassword();
    /* 이메일과 비밀번호에 변화가 있을 때 watch */
    watchEffect(() => checkPasswordForm({
      email: email.value,
      nickname: nickname.value,
      birth: birth.value
      })
    )

    return {
      email,
      nickname,
      birth,
      error,
      checkPasswordForm,
      findPassword
    };
  }
}
</script>