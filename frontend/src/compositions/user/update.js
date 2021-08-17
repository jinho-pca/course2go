import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import PV from 'password-validator';
import { BASE_URL } from '@/compositions/global.js';
import jwt from 'jsonwebtoken'

const { URL, token } = BASE_URL()

export const userUpdate = () => {
  const router = useRouter();

  const user = jwt.decode(token.substr(7))
  const passwordSchema = ref(new PV());
  const nicknameSchema = ref(new PV());
  const nameSchema = ref(new PV());
  const error = ref({
    nickname: false,
    password: false,
    passwordConfirm: false,
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
  const email = ref(user.userEmail);
  const password = ref('');
  const passwordConfirm = ref('');
  const nickname = ref(user.userNickname);
  
  const update = async ()=> {
    if(isSubmit.value) {
      const data = {
        userPassword: password.value,
        userNickname: nickname.value,
      };
      isSubmit.value = false;
      await axios({
        method: 'put',
        url: URL + 'user/modify',
        data: data,
        headers: {
          Authorization: token,
        }
      })
      .then((res) => {
        const token = res.headers.authorization
        localStorage.setItem('Authorization', token)
        return res
      })
      .then((res) => {
        isSubmit.value = true;
        alert('회원 정보 수정이 완료되었습니다.')
        router.go(-1)
        return res
      })
      .catch((err) => {
        isSubmit.value = true;
        console.log(err)
        alert('회원 정보 수정에 실패했습니다.')
        return err
      })
    }
  };
  /* Signup 통신 End */

  /* Signup form check Start */
  const checkUpdateForm = ()=> {
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
    password, 
    passwordConfirm,
    nickname,
    error,
    update,
    isSubmit,
    passwordSchema,
    checkUpdateForm,
    nicknameSchema,
    checkNickname,
    user
  }
}

export const userDelete = () => {
  const router = useRouter();
  const del = () => {
    axios({
      method: 'delete',
      url: URL + 'user/delete',
      headers: {
        Authorization: token,
      }
    })
    .then((res) => {
      router.push("/")
      localStorage.removeItem("Authorization")
      return res
    })
    .catch((err) => {
      console.log(err)
    })
  }

  const deleteUser = () => {
    if (confirm("정말 삭제하시겠습니까?") == true) {
      del()
    } else {
      return false
    }
  }

  return { deleteUser }
}
