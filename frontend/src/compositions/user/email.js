import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import PV from 'password-validator';
import { BASE_URL } from '@/compositions/global.js'

const { URL } = BASE_URL()
export const userEmail = () => {
  const router = useRouter();

  const nickname = ref('');
  const birth = ref('');

  const nicknameSchema = ref(new PV());
  nicknameSchema.value
  .is()
  .min(2)
  .is()
  .max(10);

  const error = ref({
    nickname: false,
    birth: false
  });
  const isSubmit = ref(false);

  const findEmail = async () => {
    if(isSubmit.value) {
      const data = {
        userNickname: nickname.value,
        userBirthday: birth.value
      };
      isSubmit.value = false;

      await axios({
        method: 'post',
        url: URL + 'user/find-email',
        data: data,
      })
      .then((res) => {
        alert(`${data.userNickname}님의 이메일은 ${res.data.object} 입니다.`)
        return res.data.object
      })
      .then((res) => {
        isSubmit.value = true;
        router.push("/")
        return res
      })
      .catch((err) => {
        isSubmit.value = true;
        alert('이메일을 찾을 수 없습니다.')
        return err
      })
    }
  };
/* 이메일 찾기 통신 END */
/* Check Email Find Form START */
  const checkEmailForm = ()=> {
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
  /* Check Email Find Form END */
  return {
    nickname,
    birth,
    error,
    isSubmit,
    nicknameSchema,
    findEmail,
    checkEmailForm
  }
}