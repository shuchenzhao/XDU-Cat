import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    meta:{title:"西电流浪猫之家 XDUcat"},
    component: HomeView
  },
  {
    path: '/login',
    name: '用户登录',
    component: () => import('../views/LoginView.vue')
  },
  {
    path: '/manage',
    name: '管理员页面',
    component: () => import('../views/Manage/ManageView.vue'),
    children: [
      {
        path: 'editCat',
        component: () => import('../views/Manage/EditCat/EditCatView.vue')
      }, {
        path: 'addAdmin',
        component: () => import('../views/Manage/AddAdmin/AddAdminView.vue')
      }, {
        path: 'password',
        component: () => import('../views/Manage/Password/passwordView.vue')
      }, {
        path: 'comment',
        component: () => import('../views/Manage/Comment/commentView.vue')
      }
    ]
  },
  {
    path: '/searchResult',
    name: '猫咪搜索结果',
    component: () => import('../views/SearchResultView.vue')
  },
  {
    path: '/refresh',
    name: '刷新',
    component: () => import('../views/RefreshView.vue')
  },
  {
    path: '/catDetail',
    name: '猫咪详细信息',
    component: () => import('../views/CatDetailView.vue')
  },
  {
    path: '/:catchAll(.*)',
    name: '页面不存在',
    component: () => import('../views/NotFoundView.vue')
  },
  {
    path: '/*',
    redirect: '/home'
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
