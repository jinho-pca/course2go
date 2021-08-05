import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import axios from 'axios';
import jwt from 'jsonwebtoken'
import PV from 'password-validator';
import * as EmailValidator from 'email-validator';
import { BASE_URL } from '@/compositions/global.js'

const { URL } = BASE_URL();

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
        userEmail: email.value,
        userPassword: password.value
      };
      isSubmit.value = false;

      await axios({
        method: 'post',
        url: URL + 'user/login',
        data: data,
        headers:{
          'Content-Type': 'application/x-www-form-urlencoded',
          'Accept': 'application/json; charset=utf-8',
        }
      })
      .then((res) => {
        isSubmit.value = true;
        const token = res.headers.authorization.substr(7)
        localStorage.setItem('Authorization', token)
        const data = jwt.decode(token)
        return data
      })
      .then((res) => {
        const user = {
          userEmail: res.userEmail,
          userNickname: res.userNickname
        }
        store.dispatch('getUser', user)
        return res
      })
      .then((res) => {
        router.push("/newsfeed")
        return res
      })
      .catch((err) => {
        console.log(err)
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