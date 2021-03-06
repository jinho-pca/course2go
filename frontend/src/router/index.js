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
    path: '/routeArticle',
    name: 'RouteArticle',
    component: () => import(/* webpackChunkName: "RouteArticle" */ '../views/article/RouteArticle.vue')
  },
  {
    path: '/visitArticle',
    name: 'VisitArticle',
    component: () => import(/* webpackChunkName: "VisitArticle" */ '../views/article/VisitArticle.vue')
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
  {
    path: '/notice',
    name: 'Notice',
    component: () => import(/* webpackChunkName: "Notice" */ '../views/Notice.vue')
  },
  {
    path: '/search',
    name: 'Search',
    component: () => import(/* webpackChunkName: "Search" */ '../views/Search.vue')
  },
]

const router = createRouter({
  mode: 'history',
  history: createWebHistory(process.env.BASE_URL),
  routes,
  scrollBehavior (to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { top: 0 }
    }
  },
})

export default router
