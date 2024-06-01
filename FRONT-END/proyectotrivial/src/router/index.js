import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import RankingView from '../views/RankingView.vue'
import CuentaView from '../views/CuentaView.vue'
import NoticiasView from '../views/NoticiasView.vue'
import PreguntasView from '../views/PreguntasView.vue'
import AdminsView from '../views/usuarios/AdminsView.vue'
import InsertarUsuarioView from '../views/usuarios/InsertarUsuarioView.vue'
import ActualizarUsuarioView from '../views/usuarios/ActualizarUsuarioView.vue'
import SeleccionDificultad from '../components/SeleccionDificultad.vue';
import PoliticaProteccionDatos from '../components/PoliticaProteccionDatos.vue';
import PoliticaCookies from '../components/PoliticaCookies.vue';
import AvisoLegal from '../components/AvisoLegal.vue';
import RegistrarView from '../views/RegistrarView';
import LoginView from '../views/LoginView'

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
    path: '/cuenta/:name/:score/:descripcion',
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
    path: '/admin/usuarios/:userId/editarUsuario',
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
    path: '/registrar',
    name: 'registrarView',
    component: RegistrarView
  },
  {
    path: '/login',
    name: 'loginView',
    component: LoginView
  },


  /*{
    path: '/admin',
    name: 'admin',
    component: Admin,
   /* meta: { requiresAuth: true }, 
  } */
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});



export default router;