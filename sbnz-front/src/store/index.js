import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    activeReservations: [],
    model: '',
    users: [],
    badgeShow:true,
  },
  mutations: {
    hideBadge(state){
      state.badgeShow=false;
    },
    showBadge(state){
      state.badgeShow=true;
    },
    addModel(state, model) {
      state.model = model;
    },
    fetchUsers(state, users) {
      state.users.length = 0;
      users.forEach((user) => {
        state.users.push(user);
      });
    },
    fetchActiveReservations(state, reservations) {
      state.activeReservations.length = 0;
      reservations.forEach((reservation) => {
        state.activeReservations.push(reservation);
      });
    },
    removeActiveReservation(state, id) {
      state.activeReservations = state.activeReservations.filter((res) => res.id !== id);
    },
    removeActiveReservations(state) {
      state.activeReservations.length = 0;
    },
  },
  actions: {
  },
  modules: {
  }
})
