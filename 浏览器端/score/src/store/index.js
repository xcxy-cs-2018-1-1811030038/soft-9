import { createStore } from 'vuex'

export default createStore({
  state: {
    token: '',
    userType: '',
    name: '',
    id: '',
  },
  mutations: {
    setToken(state, token) {
      state.token = token;
    },
    setUserType(state, userType) {
      state.userType = userType;
    },
    setName(state, name) {
      state.name = name;
    },
    setId(state, id) {
      state.id = id;
    }
  },
  actions: {
    // login(context) {

    // }
  },
})
