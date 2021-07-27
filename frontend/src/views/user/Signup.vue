<template>
  <div id="signup-wrap">
    <div class="signup-html">
      <h3 id="tab-1" name="tab" class="tab">회원가입</h3>
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
              <input type="button" value="중복확인" class="check-button">
            </div>
            <div class="error-text" v-if="error.email">{{error.email}}</div>
          </div>
          <div class="group">
            <label for="email" class="label">이름</label>
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
            <label for="email" class="label">비밀번호</label>
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
            <label for="email" class="label">비밀번호 확인</label>
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
            <label for="email" class="label">닉네임</label>
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
              <input type="button" value="중복확인" class="check-button">
            </div>
          </div>
          <div class="group">
            <label for="password" class="label">생년월일</label>
            <div class="input-border">
              <input
              v-model="email"
              v-bind:class="{error: error.email, complete: !error.email&&email.length!==0}"
              placeholder="이메일을 입력하세요"
              type="date" 
              id="email"
              name="email"
              class="input"
              autocapitalize="off"
              >
            </div>
            <div class="error-text" v-if="error.password">{{error.password}}</div>
          </div>
          <div class="group">
            <label for="password" class="label">성별</label>
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
            <div class="error-text" v-if="error.password">{{error.password}}</div>
          </div>
          <div class="group">
            <label for="password" class="label">성별</label>
            <div class="toggle-radio">
              <input type="radio" name="rdo" id="male" checked>
              <input type="radio" name="rdo" id="female">
              <div class="switch">
                <label for="male">남성</label>
                <label for="female">여성</label>
              </div>
            </div>
            <div class="error-text" v-if="error.password">{{error.password}}</div>
          </div>
          <div class="group align-buttons">
            <input type="submit" @click="signup" value="확인" class="button">
            <input type="submit" @click="signup" value="취소" class="button">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/user/signup.css';
import { watchEffect } from 'vue';
import { userLogin } from '@/compositions/user';

export default {
  name: 'singup',
  setup() {
    const { email, password, error, isSubmit, component, passwordSchema, login, checkForm } = userLogin();
    /* 이메일과 비밀번호에 변화가 있을 때 watch */
    watchEffect(() => checkForm({ password: password.value, email: email.value}))

    return { 
      email,
      password,
      error,
      isSubmit,
      component,
      passwordSchema,
      login,
      checkForm,
    };
  }
}
</script>