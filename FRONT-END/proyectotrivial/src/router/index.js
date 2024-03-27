import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import RankingView from '../views/RankingView.vue'
import CuentaView from '../views/CuentaView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/ranking',
    name: 'ranking',
    component: RankingView
  },
  {
    path: '/cuenta',
    name: 'cuenta',
    component: CuentaView
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
