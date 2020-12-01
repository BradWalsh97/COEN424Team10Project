<template>
  <q-page class="full-width row justify-center items-center">
    <div class="bg-tint login-container">
      <!-- The inputs -->
      <h3 class="text-primary" style="margin-top: 0 !important;">Login</h3>

      <div style="">
        <q-input color="dark" outlined class="fill-input" v-model="account.username" label="username" />
      </div>
      <div style="padding-top: 5%">
        <q-input
          outlined
          class="fill-input"
          color="dark"
          v-model="account.password"
          :type="isPwd ? 'password' : 'text'"
          label="Password"
        >
          <template v-slot:append>
            <q-icon
              :name="isPwd ? 'visibility_off' : 'visibility'"
              class="cursor-pointer"
              @click="isPwd = !isPwd"
            />
          </template>
        </q-input>
      </div>

      <div style="padding-top: 5%" class="row justify-between">
        <div>
          <q-btn label="Login" outline color="primary" @click="login()" />
        </div>
        <div>
          <q-btn label="Sign Up" color="primary" @click="signUp()" flat />
        </div>
      </div>
    </div>


  </q-page>
</template>

<script>
import baseURL from "url";
import Axios from "axios";
const urlSchema = require("../SizzleUrls").default;

export default {
  name: "Register",
  data() {
    return {
      //baseUrl: baseURL.baseURL + "/profile/",
      baseUrl: "http://localhost/api/profile/",
      account: {
        username: "",
        password: "",
      },
      isPwd: true,
    };
  },
  methods: {
    signUp() {
      this.$router.push("/register");
    },
    login() {
      Axios.post(`${urlSchema.profileUrl}authenticate`, this.account)
        .then((res) => this.checkResult(res.data))
        .catch((err) => console.log("Login", err));
    },
    checkResult(result) {
      if (result) {
        console.log("checking result", result);
        console.log("store", this.$store);
        this.$store.commit("example/LOGGED_IN", this.account.username);
        this.$router.push("/home");
      }
    },
  },
  beforeMount() {
    console.log("Before mount login");
  },
};
</script>

<style lang="scss" scoped>
.login-container {
  min-width: 50%; 
  padding: 3em 2em;
  border-radius: 3px;
}
</style>