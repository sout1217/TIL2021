import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/error',
    name: 'error',
    component: () => import('@/views/ErrorPage.vue'),
  },
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ '../views/About.vue'),
  },
  {
    path: '/guest',
    component: () => import('../views/GuestPage'),
  },
  {
    path: '/user',
    component: () => import('../views/UserPage'),
    meta: {
      permissions: [
        {
          role: 'guest',
          access: false,
          redirect: 'error',
        },
      ],
    },
  },
  {
    path: '/silver',
    component: () => import('../views/UserPage'),
    meta: {
      permissions: [
        {
          role: 'user',
          access: user => user.level > 1,
          redirect: 'error',
        },
        {
          role: 'guest',
          access: false,
          redirect: 'error',
        },
      ],
    },
  },
  {
    path: '/admin',
    component: () => import('../views/AdminPage'),
    meta: {
      permissions: [
        {
          role: 'guest',
          access: false,
          redirect: 'error',
        },
        {
          role: 'user',
          access: false,
          redirect: 'error',
        },
      ],
    },
  },
  {
    path: '*',
    redirect: '/error',
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
