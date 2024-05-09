import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import RankingView from '../views/RankingView.vue'
import CuentaView from '../views/CuentaView.vue'
import PreguntasView from '../views/PreguntasView.vue'
import AdminsView from '../views/usuarios/AdminsView.vue'
import InsertarUsuarioView from '../views/usuarios/InsertarUsuarioView.vue'
import ActualizarUsuarioView from '../views/usuarios/ActualizarUsuarioView.vue'
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
    path: '/cuenta/:name/:score',
    name: 'CuentaView',
    component: CuentaView
  },
  {
    path: '/cuenta',
    name: 'CuentaViewWithoutParams',
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
    path: '/admin/actualizarUser',
    name: 'ActualizarUsuarioView',
    component: ActualizarUsuarioView
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

router.beforeEach((to, from, next) => {
  // Agrega la clase 'no-scroll' al body si la ruta es '/'
  if (to.path === '/') {
    document.body.classList.add('no-scroll');
  } else {
    document.body.classList.remove('no-scroll');
  }
  next();
});

export default router
