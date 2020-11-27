export function someMutation(/* state */) {}

export function LOGGED_IN(state, id) {
  state.id = id;
  state.authenticated = true;
}
export function LOGGED_OUT(state) {
  state.id = "";
  state.authenticated = false;
}
