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
    path: '/map',
    name: 'map',
    component: () => import('../components/maps/kakaomap/Kakaomap.vue')
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
  {
    path: '/profile',
    name: 'Profile',
    component: () => import(/* webpackChunkName: "Profile" */ '../views/profile/Profile.vue')
  },
  {
    path: '/profile/modify',
    name: 'UpdateProfile',
    component: () => import(/* webpackChunkName: "UpdateProfile" */ '../views/profile/UpdateProfile.vue')
  },
  {
    path: '/modify',
    name: 'UpdateUser',
    component: () => import(/* webpackChunkName: "UpdateUser" */ '../views/profile/UpdateUser.vue')
  },
  {
    path: '/follow',
    name: 'Follow',
    component: () => import(/* webpackChunkName: "Follow" */ '../views/profile/Follow.vue')
  },
  {
    path: '/alert',
    name: 'Alert',
    component: () => import(/* webpackChunkName: "Alert" */ '../views/Alert.vue')
  },
  {
    path: '/routeArticle',
    name: 'RouteArticle',
    component: () => import(/* webpackChunkName: "Profile" */ '../views/article/RouteArticle.vue')
  },
  {
    path: '/write/route',
    name: 'WriteRoute',
    component: () => import(/* webpackChunkName: "WriteRoute" */ '../views/WriteRoute.vue')
  },
  {
    path: '/write/visit',
    name: 'WriteVisit',
    component: () => import(/* webpackChunkName: "WriteVisit" */ '../views/WriteVisit.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
