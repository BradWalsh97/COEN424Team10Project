<template>
  <q-layout view="lHh Lpr lFf">
    <div class="">
      <q-header class="header-bar">
        <q-toolbar class="text-primary bold">
          <div class="full-width row justify-between">
            <div></div>
            <div>
              <q-btn label="Log out" flat color="primary" @click="logOut()" />
            </div>
          </div>
        </q-toolbar>
      </q-header>

      <q-page-container>
        <router-view />
      </q-page-container>
    </div>
  </q-layout>
</template>

<script>
export default {
  name: "MainLayout",
  components: {},
  data() {
    return {
      tab: "home",
    };
  },
  methods: {
    reach(path) {
      if (this.$router.currentRoute.fullPath !== path) this.$router.push(path);
    },
    logOut() {
      this.$store.commit("example/LOGGED_OUT");
      this.$router.push("/");
    },
  },
};
</script>

<style>
.header-bar {
  background: transparent !important;
  /* border-bottom: 1px solid white; */
}
</style>
