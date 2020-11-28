// export function someAction(/* context */) {}
export function userLoggedIn({ commit }, id) {
  commit("LOGGED_IN", id);
}

export function userLoggedOut({ commit }) {
  commit("LOGGED_OUT");
}
