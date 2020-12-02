<template>
  <div style="width: 50%" class="text-primary">
    <div style="padding-top: 1%">
      <q-input
        outlined
        class="fill-input"
        color="dark"
        bg-color="brown-5"
        v-model="currentEmail"
        label="Current Email"
        :rules="[(val) => !!val || 'Field is required']"
      />
    </div>
    <div style="padding-top: 1%">
      <q-input
        outlined
        class="fill-input"
        bg-color="brown-5"
        color="dark"
        v-model="newEmail"
        label="New Email"
        :rules="[(val) => !!val || 'Field is required']"
      />
    </div>

    <div style="padding-top: 2%">
      <q-btn color="brown-5" label="Change Email" />
    </div>
  </div>
</template>

<script>
import Axios from "axios";
const urlSchema = require("../SizzleUrls").default;

export default {
  name: "ProfileChangeEmail",
  data() {
    return {
      currentEmail: "",
      newEmail: "",
      user: "",
    };
  },
  methods: {
    changeEmail() {
      //TODO Fix this url
      Axios.post(`${urlSchema.profileUrl}changeEmail/${this.newEmail}`);
    },
  },
  mounted() {
    this.user = this.$store.getters["example/getUser"];
  },
};
</script>

<style></style>
