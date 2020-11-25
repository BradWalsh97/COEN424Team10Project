<template>
  <q-page class="full-height" style="padding-top: 1%">
    <div>
      <div style="padding-left: 5%; padding-bottom: 2%">
        <q-btn label="DONE" icon="keyboard_backspace" @click="goBackHome" />
      </div>
      <q-splitter v-model="splitterModel" style="height: 60%">
        <template v-slot:before>
          <q-tabs
            v-model="innerTab"
            vertical
            align="left"
            inline-label
            class="text-primary"
          >
            <q-tab name="name" icon="account_box" label="Change name" />
            <q-tab name="password" icon="vpn_key" label="Change Password" />
            <q-tab name="delete" icon="delete" label="Delete Account" />
          </q-tabs>
        </template>

        <template v-slot:after>
          <q-tab-panels
            v-model="innerTab"
            animated
            keep-alive
            transition-prev="slide-down"
            transition-next="slide-up"
          >
            <q-tab-panel name="name">
              <div class="q-mb-md"><h3>Change Email</h3></div>
              <ProfileChangeEmail />
            </q-tab-panel>

            <q-tab-panel name="password">
              <div class="q-mb-md"><h3>Change Password</h3></div>
              <ProfileChangePassword />
            </q-tab-panel>

            <q-tab-panel name="delete">
              <div class="q-mb-md"><h3>Delete Your Account</h3></div>
              <ProfileDeleteAccount />
            </q-tab-panel>
          </q-tab-panels>
        </template>
      </q-splitter>
    </div>
  </q-page>
</template>

<script>
import Axios from "axios";
import ProfileChangeEmail from "../components/ProfileChangeName";
import ProfileChangePassword from "../components/ProfileChangePassword";
import ProfileDeleteAccount from "../components/ProfileDeleteAccount";

export default {
  name: "Profile",
  components: {
    ProfileChangeEmail,
    ProfileChangePassword,
    ProfileDeleteAccount,
  },
  data() {
    return {
      baseUrl: "http://localhost:8181/profile/",
      innerTab: "name",
      splitterModel: 20,
    };
  },
  methods: {
    goBackHome() {
      this.$router.push("/");
    },
  },
};
</script>

<style scoped></style>
