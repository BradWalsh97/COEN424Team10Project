// export function someGetter(/* state */) {}
export function getUser(state) {
  return state.id;
}
export function isAuthenticated(state) {
  return state.authenticated;
}
