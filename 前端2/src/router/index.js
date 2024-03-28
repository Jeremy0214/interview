import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import { useAuthStore } from '../stores/auth.js'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../components/LoginComponent.vue')
    },
    {
      path: '/',
      name: 'home',
      component: HomeView,
      children: [
        // {
        //   path: '/',
        //   name: 'home',
        //   component: HomeView
        // },
        {
          path: '/test1',
          name: 'test1',
          // route level code-splitting
          // this generates a separate chunk (About.[hash].js) for this route
          // which is lazy-loaded when the route is visited.
          component: () => import('../views/test1.vue')
        },
        {
          path: '/test2',
          name: 'test2',
          // route level code-splitting
          // this generates a separate chunk (About.[hash].js) for this route
          // which is lazy-loaded when the route is visited.
          component: () => import('../views/test2.vue')
        },
        {
          path: '/test3',
          name: 'test3',
          // route level code-splitting
          // this generates a separate chunk (About.[hash].js) for this route
          // which is lazy-loaded when the route is visited.
          component: () => import('../views/test3.vue')
        },
        {
          path: '/test4',
          name: 'test4',
          // route level code-splitting
          // this generates a separate chunk (About.[hash].js) for this route
          // which is lazy-loaded when the route is visited.
          component: () => import('../views/test4.vue')
        },
        {
          path: '/test5',
          name: 'test5',
          // route level code-splitting
          // this generates a separate chunk (About.[hash].js) for this route
          // which is lazy-loaded when the route is visited.
          component: () => import('../views/test5.vue')
        },
        {
          path: '/Practice',
          name: 'Practice',
          // route level code-splitting
          // this generates a separate chunk (About.[hash].js) for this route
          // which is lazy-loaded when the route is visited.
          component: () => import('../views/Practice.vue')
        },
        {
          path: '/ProductView',
          name: 'ProductView',
          // route level code-splitting
          // this generates a separate chunk (About.[hash].js) for this route
          // which is lazy-loaded when the route is visited.
          component: () => import('../views/ProductView.vue')
        }
      ]
    }
  ]
})
router.beforeEach(async (to) => {
  if (to.name != 'login') {
    const store = useAuthStore()
    // console.log(store.token);
    const isAuthenticated = store.token !== null || localStorage.getItem('token') != null

    if (!isAuthenticated) {
      return 'login'
    }
  }
})
export default router
