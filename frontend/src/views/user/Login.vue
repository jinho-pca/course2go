<template>
  <div id="login-wrap">
    <div class="login-html">
      <input id="tab-1" type="radio" name="tab" class="sign-in"><label for="tab-1" class="tab">로그인</label>
      <div class="login-form">
        <div class="sign-in-htm">
          <div class="group">
            <label for="email" class="label">이메일</label>
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
            <div class="error-text" v-if="error.email">{{error.email}}</div>
          </div>
          <div class="group">
            <label for="password" class="label">비밀번호</label>
            <input
            v-model="password"
            type="password"
            v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
            id="password"
            name="password"
            class="input"
            @keyup.enter="login"
            placeholder="비밀번호를 입력하세요."
            >
            <div class="error-text" v-if="error.password">{{error.password}}</div>
          </div>
          <div class="group">
            <input id="check" type="checkbox" class="check" checked>
            <label for="check"><span class="icon"></span> Keep me Signed in</label>
          </div>
          <div class="group">
            <input type="submit" @click="login" value="login" class="button">
          </div>
          <router-link to="/signup" class="group">
            <input type="submit" value="회원가입" class="button">
          </router-link>
        </div>
      </div>
      <div class="hr"></div>
      <div class="foot-lnk">
        <router-link to="/email">Forgot Email?</router-link>
        <br>
        <br>
        <router-link to="/password">Forgot Password?</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import '../../assets/css/user/login.css';
import { watchEffect } from 'vue';
import { userLogin } from '@/compositions/user';

export default {
  name: 'login',
  setup() {
    const { email, password, error, isSubmit, passwordSchema, login, checkLoginForm } = userLogin();
    /* 이메일과 비밀번호에 변화가 있을 때 watch */
    watchEffect(() => checkLoginForm({ password: password.value, email: email.value}))

    return { 
      email,
      password,
      error,
      isSubmit,
      passwordSchema,
      login,
      checkLoginForm
    };
  }
}
</script>