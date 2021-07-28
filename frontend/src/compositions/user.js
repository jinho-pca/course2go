import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import axios from 'axios';
import PV from 'password-validator';
import * as EmailValidator from 'email-validator';

export const userLogin = () => {
  const router = useRouter()
  const store = useStore()

  const passwordSchema = ref(new PV());
  const error = ref({
    email: false,
    password: false
  });
  const isSubmit = ref(false);
  const component = ref(this);

  component.value = this;
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
        url: 'http://localhost:8080/user/login/',
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
  const checkForm = ()=> {
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

  return {email, password, error, isSubmit, component, passwordSchema, login, checkForm}
}

export const userSignup = () => {

}