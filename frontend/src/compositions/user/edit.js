import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import axios from 'axios';
import PV from 'password-validator';
import * as EmailValidator from 'email-validator';
import { BASE_URL } from '@/compositions/global.js';

const { URL } = BASE_URL()

export const userSignup = () => {
  const router = useRouter();
  const store = useStore();

  const passwordSchema = ref(new PV());
  const nicknameSchema = ref(new PV());
  const nameSchema = ref(new PV());
  const error = ref({
    email: false,
    name: false,
    password: false,
    passwordConfirm: false,
    nickname: false,
    birth: false,
    gender: false
  });
  const isSubmit = ref(false);

  passwordSchema.value
    .is()
    .min(8)
    .is()
    .max(100)
    .has()
    .digits()
    .has()
    .letters();
  nicknameSchema.value
    .is()
    .min(2)
    .is()
    .max(10);
  nameSchema.value
    .is()
    .min(2)
    .is()
    .max(10)
    .not()
    .digits();

  /* Signup 통신 Start */
  const email = ref('');
  const password = ref('');
  const passwordConfirm = ref('');
  const nickname = ref('');
  const name = ref('');
  // const birth = ref(new Date());
  // 어떤 형식으로 해야 하는지 알아보기
  const birth = ref(true);
  const gender = ref('');
  // 나중에 눈 아이콘을 클릭했을 때 비밀번호가 보이도록 하기 위함
  // const passwordType = ref('password');
  // const passwordConfirmType = ref('password');
  
  const signup = async ()=> {
    if(isSubmit.value) {
      const data = {
        userEmail: email.value,
        userPassword: password.value,
        userName: name.value,
        userNickname: nickname.value,
        userBirthday: birth.value,
        userGender: gender.value,
      };
      isSubmit.value = false;
      console.log(data)
      await axios({
        method: 'post',
        url: URL + 'user/signup/',
        data: data,
      })
      .then((res) => {
        // 여기서 오류나면 data를 user에 맞게 다시 넣어줘야 함
        store.dispatch('getUser', data)
        return res
      })
      .then((res) => {
        isSubmit.value = true;
        alert('회원가입이 완료되었습니다.')
        router.push("/newsfeed")
        return res
      })
      .catch((err) => {
        isSubmit.value = true;
        console.log(err)
        alert('회원가입에 실패했습니다.')
        return err
      })
    }
  };
  /* Signup 통신 End */

  /* Signup form check Start */
  const checkSignupForm = ()=> {
    if (email.value.length >= 0 && !EmailValidator.validate(email.value))
      error.value.email = "이메일 형식이 아닙니다.";
    else error.value.email = false;

    if (
      name.value.length >= 0 &&
      !nameSchema.value.validate(name.value)
      )
      error.value.name = "이름을 정확하게 입력해주세요.";
    else error.value.name = false;

    if (password.value.length >= 0 && !passwordSchema.value.validate(password.value)
    )
      error.value.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
    else error.value.password = false;

    if (
      passwordConfirm.value.length >= 0 &&
      passwordConfirm.value !== password.value
    )
      error.value.passwordConfirm = "비밀번호가 일치하지 않습니다.";
    else error.value.passwordConfirm = false;

    if (
      nickname.value.length >= 0 &&
      !nicknameSchema.value.validate(nickname.value)
    )
      error.value.nickname = "닉네임은 2글자 이상 10글자 미만이어야 합니다.";
    else error.value.nickname = false;

    if (birth.value.length === 0) 
      error.value.birth = "생년월일을 입력해주세요.";
    else error.value.birth = false;

    /* gender은 기본 설정이 있어서 할 필요가 없지만 일단 입력 */
    if (gender.value.length === 0)
      error.value.gender = "성별을 선택해주세요."
    else error.value.gender = false;

    let submit = true;
    Object.values(error.value).map(v => {
      if (v) submit = false;
    });
    isSubmit.value = submit;
  };
  /* Signup form check End */
  /*  */
  /* 회원가입 END */
  /*  */

  /*  */
  /* Email 중복 체크 START */
  /*  */
  const checkEmail = () => {
    axios({
      method: 'get',
      url: URL + 'user/signup/check/email',
      params: {
        userEmail: email.value
      }
    })
    .then((res) => {
      alert('사용 가능합니다.')
      return res
    })
    .catch((err) => {
      console.log(err)
      alert('이미 가입한 이메일입니다.')
      return err
    });
  }
  /*  */
  /* Email 중복 체크 END */
  /*  */

  /*  */
  /* Nickname 중복 체크 START */
  /*  */
  const checkNickname = () => {
    axios({
      method: 'get',
      url: URL + 'user/signup/check/nickname',
      params: {
        userNickname: nickname.value
      }
    })
    .then((res) => {
      alert('사용 가능합니다.')
      return res
    })
    .catch((err) => {
      console.log(err)
      alert('중복된 닉네임입니다.')
      return err
    });
  }
  /*  */
  /* Nickname 중복 체크 END */
  /*  */

  return {
    email,
    name, 
    password, 
    passwordConfirm,
    nickname,
    birth,
    gender,
    error,
    isSubmit,
    passwordSchema,
    nicknameSchema,
    signup, 
    checkSignupForm,
    checkEmail,
    checkNickname
  }
}