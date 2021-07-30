import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'Login',
    component: () => import(/* webpackChunkName: "Login" */ '../views/user/Login.vue')
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
  {
    path: '/newsfeed',
    name: 'Newsfeed',
    component: () => import(/* webpackChunkName: "Newsfeed" */ '../views/Newsfeed.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
