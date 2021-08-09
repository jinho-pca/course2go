<template>
  <div id="signup-wrap">
    <div class="signup-html">
      <h3 id="tab-1" name="tab" class="tab">회원 정보 수정</h3>
      <div class="signup-form">
        <div class="sign-up-htm">
          <div class="group">
            <label for="email" class="label">이메일</label>
            <div class="input-border">
              이메일@이메일.이메일
            </div>
          </div>
          <div class="group">
            <label for="name" class="label">이름</label>
            <div class="input-border">
              <input
              v-model="name"
              v-bind:class="{error: error.name, complete: !error.name&&name.length!==0}"
              placeholder="이름을 입력하세요"
              type="text"
              id="name"
              name="name"
              class="input"
              autocapitalize="off"
              >
            </div>
            <div class="error-text" v-if="error.name">{{error.name}}</div>
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
          <div class="group">
            <label for="gender" class="label">성별</label>
            <div class="toggle-radio">
              <input type="radio" name="gender" id="male" v-model="gender" value="male">
              <input type="radio" name="gender" id="female" v-model="gender" value="female">
              <div class="switch">
                <label for="male">남성</label>
                <label for="female">여성</label>
              </div>
            </div>
            <div class="error-text" v-if="error.gender">{{error.gender}}</div>
          </div>
          <div class="group align-buttons">
            <input type="submit" @click="signup" value="확인" class="button">
            <router-link to="/profile">
              <input type="submit" value="취소" class="button">
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/user/update-user.css';
export default {
  name: 'UpdateUser',
  setup() {
  }
}
</script>