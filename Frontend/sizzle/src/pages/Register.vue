<template>
  <q-page class="full-width row justify-center">
    <div class="" style="min-width: 50%; padding-top: 5%">
      <!-- The inputs -->
      <h3>Register your new Sizzle account</h3>
      <div>
        <q-input
          outlined
          v-model="account.email"
          bg-color="brown-5"
          label="Email"
        />
      </div>

      <div style="padding-top: 5%">
        <q-input
          outlined
          v-model="account.username"
          bg-color="brown-5"
          label="username"
        />
      </div>
      <div style="padding-top: 5%">
        <q-input
          outlined
          v-model="account.password"
          :type="isPwd ? 'password' : 'text'"
          label="Password"
          bg-color="brown-5"
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
          <q-btn label="Cancel" flat @click="cancel()" />
        </div>

        <div>
          <q-btn label="Sign Up" color="primary" outline @click="signUp()" />
        </div>
      </div>
    </div>
  </q-page>
</template>

<script>
import Axios from "axios";
const urlSchema = require("../SizzleUrls").default;
export default {
  name: "Register",
  data() {
    return {
      account: { email: "", username: "", password: "" },
      isPwd: true,
    };
  },
  methods: {
    signUp() {
      Axios.put(`${urlSchema.profileUrl}new`, this.account)
        .then((res) => {
          this.$router.push("/");
          this.$store.commit("example/LOGGED_IN", this.account.username);
        })
        .catch((err) => console.log("Register", err));
    },
    cancel() {
      this.$router.push("/");
    },
  },
};
</script>
