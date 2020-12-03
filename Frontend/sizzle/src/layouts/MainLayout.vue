<template>
  <!-- <div class="q-pa-md"> -->
  <q-layout view="hHh Lpr lff">
    <!-- <div class=""> -->
    <!-- <q-page-sticky position="top"> -->
    <q-header :class="headerBackground">
      <q-toolbar class="text-primary bold">
        <div class="full-width row justify-between">
          <div>
            <q-toolbar-title class="column justify-center text-h4">
              Sizzle
            </q-toolbar-title>
          </div>
          <div
            style="display: flex; justify-content: center; align-items: center"
          >
            <q-tabs v-model="tab" inline-label dense class="text-primary">
              <q-tab name="home" icon="home" label="Home" />
              <q-tab name="search" icon="search" label="Search" />
              <q-tab name="cookbook" icon="book" label="Cookbooks" />
            </q-tabs>
          </div>

          <div class="column justify-center">
            <q-btn-dropdown :label="user" flat color="primary">
              <q-list anchor="bottom right" self="top right">
                <q-item clickable>
                  <q-item-section @click="goToAccountSettings"
                    >Account Settings</q-item-section
                  >
                </q-item>
                <q-item clickable @click="logOut">
                  <q-item-section>Log out</q-item-section>
                </q-item>
              </q-list>
            </q-btn-dropdown>
          </div>
        </div>
      </q-toolbar>
    </q-header>
    <!-- </q-page-sticky> -->
    <q-page-container>
      <q-tab-panels keep-alive animated v-model="tab">
        <q-tab-panel name="home">
          <PageIndex />
        </q-tab-panel>

        <q-tab-panel name="search">
          <Search />
        </q-tab-panel>

        <q-tab-panel name="cookbook">
          <CookBooks />
        </q-tab-panel>
      </q-tab-panels>
    </q-page-container>
    <!-- </div> -->
  </q-layout>
  <!-- </div> -->
</template>

<script>
import PageIndex from "../pages/Index";
import CookBooks from "../pages/Cookbooks";
import Search from "../pages/Search";
export default {
  name: "MainLayout",
  components: {
    PageIndex,
    CookBooks,
    Search,
  },
  data() {
    return {
      tab: "home",
      user: "",
    };
  },
  methods: {
    reach(path) {
      if (this.$router.currentRoute.fullPath !== path) this.$router.push(path);
    },
    goToAccountSettings() {
      this.$router.push("/profile");
    },
    logOut() {
      this.$store.commit("example/LOGGED_OUT");
      this.$router.push("/");
    },
  },
  beforeMount() {
    if (this.$store.getters["example/isAuthenticated"]) {
      this.user = this.$store.getters["example/getUser"];
    }
  },
  computed: {
    headerBackground: function () {
      if (this.$q.dark.isActive) {
        return `bg-dark`;
      } else {
        return `bg-white`;
      }
    },
  },
};
</script>

<style scoped>
.text-h4 {
  /* text-shadow: 2px 2px rgb(49, 49, 49); */
}

.header-bar {
  background: transparent !important;
  /* border-bottom: 1px solid white; */
}
</style>
