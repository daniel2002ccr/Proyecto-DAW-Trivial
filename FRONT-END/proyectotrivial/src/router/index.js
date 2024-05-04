import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import RankingView from '../views/RankingView.vue'
import CuentaView from '../views/CuentaView.vue'
import PreguntasView from '../views/PreguntasView.vue'
import AdminsView from '../views/usuarios/AdminsView.vue'
import InsertarUsuarioView from '../views/usuarios/InsertarUsuarioView.vue'
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
    component: AdminsView
  },
  {
    path: '/admin/crearUser',
    name: 'InsertarUsuarioView',
    component: InsertarUsuarioView
  },
  {
    path: '/seleccion-dificultad',
    name: 'seleccion-dificultad',
    component: SeleccionDificultad
  },
  { path: '/:difficulty', component: PreguntasView, props: true },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router
