export const BASE_URL = () => {
  const URL = 'http://13.209.13.47:8080/';
  // const URL = 'http://localhost:8080/';
  const token = localStorage.getItem('Authorization')
  return { URL, token }
}
