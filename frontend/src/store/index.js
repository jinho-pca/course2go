import { createStore } from 'vuex'

export default createStore({
  state: {
    user: {},
  },
  mutations: {
    GET_USER: function(state, user) {
      state.user = user
    }
  },
  actions: {
    getUser: function({commit}, user) {
      commit('GET_USER', user)
    }
  },
  getters: {
  },
  modules: {
  }
})
