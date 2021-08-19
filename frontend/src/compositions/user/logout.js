import { useRouter } from 'vue-router';

export const userLogout = () => {
  const router = useRouter()

  const logout = async () => {
    if (confirm('로그아웃 하시겠습니까?')) {
      await localStorage.removeItem('Authorization')
      alert("로그아웃 되었습니다!");
      await router.push('/')
      await location.reload()
    }
  }
  return { logout }
}