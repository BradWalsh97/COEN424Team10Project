<template>
  <div>
    <div class="text-balck">
      <p>
        Deleting your account will result in you losing your cookbooks and your
        saved
      </p>
      <p>
        The public recipes that you've created will remain visible to everyone
        else.
      </p>
    </div>
    <div class="column">
      <q-checkbox
        keep-color
        v-model="understand"
        label="I understand, delete my account"
        color="primary"
      />
      <div style="padding-top: 1%">
        <q-btn
          outline
          label="Delete"
          style="width: 75pt"
          :disable="!understand"
        />
      </div>
    </div>
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
