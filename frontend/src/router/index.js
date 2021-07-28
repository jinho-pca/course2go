import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'Login',
    component: () => import(/* webpackChunkName: "login" */ '../views/user/Login.vue')
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import(/* webpackChunkName: "Home" */ '../views/Home.vue')
  },
  {
    path: '/signup',
    name: 'Signup',
    component: () => import(/* webpackChunkName: "Signup" */ '../views/user/Signup.vue')
  },
  {
    path: '/email',
    name: 'Email',
    component: () => import(/* webpackChunkName: "EMail" */ '../views/user/Email.vue')
  },
  {
    path: '/password',
    name: 'Password',
    component: () => import(/* webpackChunkName: "Password" */ '../views/user/Password.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
