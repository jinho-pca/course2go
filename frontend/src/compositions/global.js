export const BASE_URL = () => {
  const URL = 'https://i5a106.p.ssafy.io/course2go/';
  // const URL = 'http://13.209.13.47:8080/course2go/';
  // const URL = 'http://localhost:8080/course2go/';
  const token = localStorage.getItem('Authorization')
  return { URL, token }
}