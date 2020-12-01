<template>
  <div>
    <q-drawer
    show-if-above
    :value="true"
    side="left"
    behavior="desktop"
    class="profile-drawer"
    >
      <div class="h-100" style="display: flex; flex-direction: column; align-items: stretch">
        <q-btn color="primary" outline label="DONE" icon="keyboard_backspace" 
        style="align-self: flex-start; margin: 0.5em 1em;" @click="goBackHome" />
      
        <div class="text-h5 profile-header">
          Account Settings
        </div>
        
        <q-tabs
          v-model="innerTab"
          vertical
          align="right"
          inline-label
          class="text-primary" style="flex: 1">
          <q-tab name="name" icon="account_box" label="Change name" />
          <q-tab name="password" icon="vpn_key" label="Change Password" />
          <q-tab name="delete" icon="delete" label="Delete Account" />
        </q-tabs>

        <div class="text-h4 text-primary" style="padding: 0.5em 0.5em 0 0.5em; margin-bottom: 0.5em">Sizzle</div>

      </div>
    </q-drawer>
    <q-tab-panels
      v-model="innerTab"
      animated
      keep-alive
      transition-prev="slide-down"
      transition-next="slide-up"
      >
      <q-tab-panel name="name">
      <div class="q-mb-md text-primary"><h3>Change Email</h3></div>
      <ProfileChangeEmail />
      </q-tab-panel>

      <q-tab-panel name="password">
      <div class="q-mb-md text-primary"><h3>Change Password</h3></div>
      <ProfileChangePassword />
      </q-tab-panel>

      <q-tab-panel name="delete">
      <ProfileDeleteAccount />
      </q-tab-panel>
    </q-tab-panels>
  </div>
</template>

<script>
import Axios from "axios";
import ProfileChangeEmail from "../components/ProfileChangeName";
import ProfileChangePassword from "../components/ProfileChangePassword";
import ProfileDeleteAccount from "../components/ProfileDeleteAccount";
const urlSchema = require("../SizzleUrls").default;

export default {
  name: "Profile",
  components: {
    ProfileChangeEmail,
    ProfileChangePassword,
    ProfileDeleteAccount,
  },
  data() {
    return {
      innerTab: "name",
      splitterModel: 20,
    };
  },
  methods: {
    goBackHome() {
      this.$router.push("/");
    },
  },
  beforeMount() {
    console.log("Before mount Profile");
    if (!this.$store.getters["example/isAuthenticated"]) {
      this.$router.push("/login");
    }
  },
};
</script>

<style lang="scss">
.profile-drawer {

  .q-drawer {
    background-color: rgba(197, 135, 64, 0.5);
    backdrop-filter: blur(5px);
    margin-top: -50px;
    padding-top: 50px;
    z-index: 0;
  }

  .profile-header {
    padding: 0 1rem;
    height: 50px;
    display: flex;
    justify-content: flex-start;
    align-items: center;
    color: white !important;
    font-weight: 400;
  }
}
</style>
