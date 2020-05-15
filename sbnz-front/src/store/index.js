import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    activeReservations: [],
  },
  mutations: {
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
