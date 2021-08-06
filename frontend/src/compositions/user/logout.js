import { useRouter } from 'vue-router';

export const userLogout = () => {
  const router = useRouter()

  const logout = () => {
    localStorage.removeItem('Authorization')
    router.push('/')
  }
  return { logout }
}