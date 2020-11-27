<template>
  <div style="width: 50%">
    <div>Change your Password</div>
    <div style="padding-top: 1%">
      <q-input
        v-model="currentPassword"
        outlined
        :type="isCurrentPwd ? 'password' : 'text'"
        label="Current Password"
        :rules="[(val) => !!val || 'Field is required']"
      >
        <template v-slot:append>
          <q-icon
            :name="isCurrentPwd ? 'visibility_off' : 'visibility'"
            class="cursor-pointer"
            @click="isCurrentPwd = !isCurrentPwd"
          />
        </template>
      </q-input>
    </div>
    <div style="padding-top: 1%">
      <q-input
        v-model="newPassword"
        outlined
        :type="isNewPwd ? 'password' : 'text'"
        label="New Password"
        :rules="[(val) => !!val || 'Field is required']"
      >
        <template v-slot:append>
          <q-icon
            :name="isNewPwd ? 'visibility_off' : 'visibility'"
            class="cursor-pointer"
            @click="isNewPwd = !isNewPwd"
          />
        </template>
      </q-input>
    </div>
    <div style="padding-top: 1%">
      <q-input
        v-model="confirmNewPassword"
        outlined
        :type="isConfirmNewPwd ? 'password' : 'text'"
        label="Confirm New Password"
        :rules="[
          (val) => val === newPassword || 'The new passwords do not match',
        ]"
      >
        <template v-slot:append>
          <q-icon
            :name="isConfirmNewPwd ? 'visibility_off' : 'visibility'"
            class="cursor-pointer"
            @click="isConfirmNewPwd = !isConfirmNewPwd"
          />
        </template>
      </q-input>
    </div>

    <div style="padding-top: 3%">
      <q-btn outline label="Change Password" @click="changePassword()" />
    </div>
  </div>
</template>

<script>
import Axios from "axios";
const urlSchema = require("../SizzleUrls").default;

export default {
  name: "ProfileChangePassword",
  data() {
    return {
      isCurrentPwd: true,
      isNewPwd: true,
      isConfirmNewPwd: true,
      currentPassword: "",
      newPassword: "",
      confirmNewPassword: "",
    };
  },
  methods: {
    checkNewPasswords() {},
    changePassword() {},
  },
};
</script>

<style scoped></style>
