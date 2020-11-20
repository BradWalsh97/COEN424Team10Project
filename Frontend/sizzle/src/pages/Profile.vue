<template>
  <q-page>
    <div></div>
  </q-page>
</template>

<script>
import Axios from "axios";

export default {
  name: "Profile",
  data() {
    return {
      baseUrl: "http://localhost:8181/profile/",
    };
  },
};
</script>

<style scoped></style>
