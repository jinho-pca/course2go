import { useRouter } from 'vue-router';

export const userLogout = () => {
  const router = useRouter()

  const logout = () => {
    if (confirm('로그아웃 하시겠습니까?')) {
      localStorage.removeItem('Authorization')
      alert("로그아웃 되었습니다!");
      router.push('/')
    }
  }
  return { logout }
}