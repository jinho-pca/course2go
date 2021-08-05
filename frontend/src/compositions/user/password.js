import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import PV from 'password-validator';
import * as EmailValidator from 'email-validator';
import { BASE_URL } from '@/compositions/global.js'

const URL = BASE_URL()

export const userPassword = () => {
  const router = useRouter();

  const email = ref('');
  const nickname = ref('');
  const birth = ref('');

  const nicknameSchema = ref(new PV());
  nicknameSchema.value
  .is()
  .min(2)
  .is()
  .max(10);

  const error = ref({
    email: false,
    nickname: false,
    birth: false
  });
  const isSubmit = ref(false);

  const findPassword = async () => {
    if(isSubmit.value) {
      const data = {
        userEmail: email.value,
        userNickname: nickname.value,
        userBirthday: birth.value
      };
      isSubmit.value = false;

      await axios({
        method: 'post',
        url: URL + 'user/find-pw',
        data: data,
      })
      .then((res) => {
        // 이메일로 새로운 비밀번호가 전송
        alert('메일로 전송된 비밀번호를 확인해주세요.')
        return res
      })
      .then((res) => {
        isSubmit.value = true;
        router.push("/")
        return res
      })
      .catch((err) => {
        isSubmit.value = true;
        alert('비밀번호를 찾을 수 없습니다.')
        return err
      })
    }
  };
/* 비밀번호 찾기 통신 END */
/* Check Password Find Form START */
  const checkPasswordForm = () => {
    if (email.value.length >= 0 && !EmailValidator.validate(email.value))
      error.value.email = "이메일 형식이 아닙니다.";
    else error.value.email = false;

    if (
      nickname.value.length >= 0 &&
      !nicknameSchema.value.validate(nickname.value)
    )
      error.value.nickname = "닉네임은 2글자 이상 10글자 미만이어야 합니다.";
    else error.value.nickname = false;

    if (birth.value.length === 0) 
      error.value.birth = "생년월일을 입력해주세요.";
    else error.value.birth = false;

    let submit = true;
    Object.values(error.value).map(v => {
      if (v) submit = false;
    });
    isSubmit.value = submit;
  };
  /* Check Password Find Form END */
  return {
    email,
    nickname,
    birth,
    error,
    isSubmit,
    nicknameSchema,
    findPassword,
    checkPasswordForm
  }
}