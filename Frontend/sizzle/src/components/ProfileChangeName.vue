<template>
  <div style="width: 50%">
    <div>First enter your current email.</div>
    <div style="padding-top: 1%">
      <q-input
        outlined
        v-model="currentEmail"
        label="Current Email"
        :rules="[(val) => !!val || 'Field is required']"
      />
    </div>
    <div style="padding-top: 1%">
      <q-input
        outlined
        v-model="newEmail"
        label="New Email"
        :rules="[(val) => !!val || 'Field is required']"
      />
    </div>

    <div style="padding-top: 2%">
      <q-btn outline label="Change Email" />
    </div>
  </div>
</template>

<script>
import Axios from "axios";
export default {
  name: "ProfileChangeEmail",
  data() {
    return {
      currentEmail: "",
      newEmail: "",
    };
  },
  methods: {
    changeEmail() {},
  },
};
</script>

<style></style>
