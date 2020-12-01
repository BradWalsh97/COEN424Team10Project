<template>
  <div style="margin-right: 300px; display: flex; flex-direction: column; align-items: center;">
    <h3 class="q-mb-md text-primary">Delete Your Account</h3>
    <div class="text-primary">
      <p>
        Deleting your account will result in you losing your cookbooks and your
        saved
      </p>
      <p>
        The public recipes that you've created will remain visible to everyone
        else.
      </p>
    </div>
      <q-checkbox
        keep-color
        v-model="understand"
        color="dark"
      >
        <label class="text-primary">I understand, delete my account</label>
      </q-checkbox>
      <q-btn
        outline
        color="primary"
        label="Delete"
        style="width: 75pt: padding-top: 1%"
        :disable="!understand"
      />
  </div>
</template>

<script>
import Axios from "axios";
const urlSchema = require("../SizzleUrls").default;

export default {
  name: "ProfileDeleteAccount",
  data() {
    return {
      understand: false,
      user: "",
    };
  },
  methods: {
    deleteProfile() {},
  },
  mounted() {
    this.user = this.$store.getters["example/getUser"];
  },
};
</script>

<style scoped></style>
