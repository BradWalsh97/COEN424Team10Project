<template>
  <q-page class="full-width row justify-center">
    <div class="" style="min-width: 50%; padding-top: 5%">
      <!-- The inputs -->
      <h3>Register your new Sizzle account</h3>
      <div>
        <q-input outlined v-model="account.email" label="Email" />
      </div>

      <div style="padding-top: 5%">
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
          <q-btn label="Cancel" flat @click="cancel()" />
        </div>

        <div>
          <q-btn label="Sign Up" color="primary" outline />
        </div>
      </div>
    </div>
  </q-page>
</template>

<script>
import Axios from "axios";
import baseURL from "url";

export default {
  name: "Register",
  data() {
    return {
      baseUrl: baseURL,
      account: { email: "", username: "", password: "" },
      isPwd: true,
    };
  },
  methods: {
    signUp() {
      Axios.put(this.baseUrl + "profile/new", this.account)
        .then((res) => {
          this.$router.push("/");
        })
        .catch((err) => console.log("Register", err));
    },
    cancel() {
      this.$router.push("/login");
    },
  },
};
</script>
