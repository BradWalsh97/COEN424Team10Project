<template>
  <q-page class="full-width row justify-center">
    <div class="" style="min-width: 50%; padding-top: 5%">
      <!-- The inputs -->
      <h3>Login</h3>

      <div style="">
        <q-input outlined v-model="account.username" label="username" />
      </div>
      <div style="padding-top: 5%">
        <q-input
          outlined
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
export default {
  name: "Register",
  data() {
    return {
      baseUrl: baseURL + "/profile/",
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
      Axios.post(this.baseUrl, this.account)
        .then((res) => this.checkResult(res.data))
        .catch((err) => console.log("Login", err));
      this.$router.push("/home");
    },
    checkResult(result) {
      if (result) {
      }
    },
  },
};
</script>
