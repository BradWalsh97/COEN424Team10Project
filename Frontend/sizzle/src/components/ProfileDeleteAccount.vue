<template>
  <div
    style="
      margin-right: 300px;
      display: flex;
      flex-direction: column;
      align-items: center;
    "
  >
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
    <q-checkbox keep-color v-model="understand" color="red">
      <label class="text-primary">I understand, delete my account</label>
    </q-checkbox>
    <q-btn
      color="brown-5"
      label="Delete"
      style="width: 75pt: padding-top: 1%"
      :disable="!understand"
      @click="deleteProfile()"
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
    deleteProfile() {
      Axios.delete(`${urlSchema.profileUrl}delete`, {
        data: {
          username: this.user,
        },
      })
        .then((res) => this.checkResult(res.data))
        .catch((err) => console.log("delete account", err));
    },

    checkResult(res) {
      if (res) {
        this.$store.commit["example/LOGGED_OUT"];
        this.$router.push("/");
      }
    },
  },
  mounted() {
    this.user = this.$store.getters["example/getUser"];
  },
};
</script>

<style scoped></style>
