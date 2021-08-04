import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import axios from 'axios';
import PV from 'password-validator';
import * as EmailValidator from 'email-validator';

/*  */
// 로그인
/*  */
const URL = 'http://localhost:8080/'

export const userLogin = () => {
  const router = useRouter()
  const store = useStore()

  const passwordSchema = ref(new PV());
  const error = ref({
    email: false,
    password: false
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

  /* login 통신 Start */
  const email = ref('');
  const password = ref('');
  const login = async ()=> {
    if(isSubmit.value) {
      const data = {
        email,
        password
      };

      isSubmit.value = false;

      await axios({
        method: 'post',
        url: URL + 'user/login/',
        data: data,
      })
      .then((res) => {
        // return받은 user data를 store로 전달해 저장
        store.dispatch('getUser', res.data)
        return res
      })
      .then((res) => {
        isSubmit.value = true;
        router.push("/home")
        return res
      })
      .catch((err) => {
        isSubmit.value = true;
        alert('Email 혹은 Password가 틀렸습니다')
        return err
      })
    }
  };
  /* Login 통신 End */

  /* Login form check Start */
  const checkLoginForm = ()=> {
    if (email.value.length >= 0 && !EmailValidator.validate(email.value))
      error.value.email = "이메일 형식이 아닙니다.";
    else error.value.email = false;

    if (
      password.value.length >= 0 &&
      !passwordSchema.value.validate(password.value)
    )
      error.value.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
    else error.value.password = false;

    let submit = true;
    Object.values(error.value).map(v => {
      if (v) submit = false;
    });
    isSubmit.value = submit;
  };
  /* Login form check End */

  return {
    email, 
    password, 
    error, 
    isSubmit, 
    passwordSchema, 
    login, 
    checkLoginForm
  }
}
/*  */
// 로그인 END
/*  */

/*  */
// 회원가입 START
/*  */
export const userSignup = () => {
  const router = useRouter()
  const store = useStore()

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
    .has()
    .letters()
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
  const birth = ref('');
  const gender = ref('');
  // 나중에 눈 아이콘을 클릭했을 때 비밀번호가 보이도록 하기 위함
  // const passwordType = ref('password');
  // const passwordConfirmType = ref('password');

  const signup = async ()=> {
    if(isSubmit.value) {
      const data = {
        email,
        password,
        name,
        nickname,
        birth,
        gender
      };

      isSubmit.value = false;

      await axios({
        method: 'post',
        url: URL + 'accounts/signup/',
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
        router.push("/home")
        return res
      })
      .catch((err) => {
        alert(email)
        isSubmit.value = true;
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
    const data = {
      email
    }
    axios({
      method: 'post',
      url: URL + 'user/email/',
      data: data,
    })
    .then((res) => {
      alert('사용 가능합니다.')
      return res
    })
    .catch((err) => {
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
    const data = {
      nickname
    }
    axios({
      method: 'post',
      url: URL + 'user/nickname/',
      data: data,
    })
    .then((res) => {
      alert('사용 가능합니다.')
      return res
    })
    .catch((err) => {
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
/*  */
// 회원가입 END
/*  */

/*  */
// 이메일 찾기 START
/*  */
/* 이메일 찾기 통신 START */
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
        nickname,
        birth
      };
      isSubmit.value = false;

      await axios({
        method: 'post',
        url: URL + 'user/email/',
        data: data,
      })
      .then((res) => {
        alert(`${data.nickname}님의 이메일은 ${res.data} 입니다.`)
        return res
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
/*  */
/* 이메일 찾기 END */
/*  */

/*  */
// 비밀번호 찾기 START
/*  */
/* 비밀번호 찾기 통신 START */
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
        email,
        nickname,
        birth
      };
      isSubmit.value = false;

      await axios({
        method: 'post',
        url: URL + 'user/password/',
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
/*  */
/* 비밀번호 찾기 END */
/*  */