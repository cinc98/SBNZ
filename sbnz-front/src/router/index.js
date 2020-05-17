import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Reservations from '../views/Reservations.vue';
import SearchResult from '../views/SearchResult.vue';

Vue.use(VueRouter)

const routes = [
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/reservations',
    name: 'Reservations',
    component: Reservations
  },
  {
    path: '/search-result',
    name: 'SearchResult',
    component: SearchResult
  },

]

const router = new VueRouter({
  routes
})

export default router
