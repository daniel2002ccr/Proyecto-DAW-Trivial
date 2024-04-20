import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import RankingView from '../views/RankingView.vue'
import CuentaView from '../views/CuentaView.vue'
import PreguntasView from '../views/PreguntasView.vue'
import AdminView from '../views/AdminView.vue'
import SeleccionDificultad from '../components/SeleccionDificultad.vue';

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
  {
    path: '/preguntas',
    name: 'preguntas',
    component: PreguntasView
  },
  {
    path: '/admin',
    name: 'admin',
    component: AdminView
  },
  {
    path: '/seleccion-dificultad',
    name: 'seleccion-dificultad',
    component: SeleccionDificultad
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
