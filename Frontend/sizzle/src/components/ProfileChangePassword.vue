<template>
  <div style="width: 50%">
    <div style="padding-top: 1%">
      <q-input
        v-model="currentPassword"
        ref="current"
        outlined
        class="fill-input"
        color="dark"
        bg-color="brown-5"
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
        ref="new"
        outlined
        class="fill-input"
        bg-color="brown-5"
        color="dark"
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
        ref="confirm"
        outlined
        class="fill-input"
        bg-color="brown-5"
        color="dark"
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
      <q-btn
        color="brown-5"
        label="Change Password"
        @click="changePassword()"
      />
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
      user: "",
    };
  },
  methods: {
    changePassword() {
      if (this.newPassword === this.confirmNewPassword) {
        Axios.post(
          `${urlSchema.profileUrl}updatePassword/${this.user}/${this.currentPassword}/${this.newPassword}`
        )
          .then(this.resetFields())
          .catch((error) => console.log("change password", error));
      }
    },
    resetFields() {
      console.log("trying to reset shit");
      this.$refs.current.resetValidation();
      this.$refs.new.resetValidation();
      this.$refs.confirm.resetValidation();
      this.currentPassword = "";
      this.newPassword = "";
      this.confirmNewPassword = "";
    },
  },

  mounted() {
    this.user = this.$store.getters["example/getUser"];
  },
};
</script>

<style scoped></style>
