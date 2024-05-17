import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import RankingView from '../views/RankingView.vue'
import CuentaView from '../views/CuentaView.vue'
import NoticiasView from '../views/NoticiasView.vue'
import PreguntasView from '../views/PreguntasView.vue'
import LoginView from '../views/LoginView.vue'
import AdminsView from '../views/usuarios/AdminsView.vue'
import InsertarUsuarioView from '../views/usuarios/InsertarUsuarioView.vue'
import ActualizarUsuarioView from '../views/usuarios/ActualizarUsuarioView.vue'
import SeleccionDificultad from '../components/SeleccionDificultad.vue';
import PoliticaProteccionDatos from '../components/PoliticaProteccionDatos.vue';
import PoliticaCookies from '../components/PoliticaCookies.vue';
import AvisoLegal from '../components/AvisoLegal.vue';
import Login from '@/components/Login.vue';
import Admin from '@/components/Admin.vue';

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
    path: '/noticias',
    name: 'NoticiasView',
    component: NoticiasView
  },
  {
    path: '/preguntas',
    name: 'preguntas',
    component: PreguntasView
  },
  /* {
    path: '/admin',
    name: 'login',
    component: LoginView
  },
  {
    path: '/login',
    name: 'admin',
    component: AdminsView
  }, */
  {
    path: '/login/crearUser',
    name: 'InsertarUsuarioView',
    component: InsertarUsuarioView
  },
  {
    path: '/login/usuarios/:userId/editarUsuario',
    name: 'ActualizarUsuarioView',
    component: ActualizarUsuarioView
  },
  {
    path: '/seleccion-dificultad',
    name: 'seleccion-dificultad',
    component: SeleccionDificultad
  },
  { path: '/:difficulty', component: PreguntasView, props: true },
  {
    path: '/protecciondatos',
    name: 'PoliticaProteccionDatos',
    component: PoliticaProteccionDatos
  },
  {
    path: '/cookies',
    name: 'PoliticaCookies',
    component: PoliticaCookies
  },
  {
    path: '/aviso-legal',
    name: 'AvisoLegal',
    component: AvisoLegal
  },
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path: '/admin',
    name: 'admin',
    component: Admin,
   /* meta: { requiresAuth: true }, */
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    const token = localStorage.getItem('token');
    if (token) {
      next();
    } else {
      next({ path: '/' });
    }
  } else {
    next();
  }
  if (to.path === '/') {
    document.body.classList.add('no-scroll');
  } else {
    document.body.classList.remove('no-scroll');
  }
  next();
});


export default router;