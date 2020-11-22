<template>
  <q-layout view="lHh Lpr lFf">
    <div class="">
      <q-header class="header-bar">
        <q-toolbar class="text-primary bold">
          <div class="full-width row justify-between">
            <div>
              <q-toolbar-title class="text-h4"> Sizzle </q-toolbar-title>
            </div>
            <div
              style="
                display: flex;
                justify-content: center;
                align-items: center;
              "
            >
              <q-tabs v-model="tab" inline-label dense class="text-primary">
                <q-tab name="home" icon="home" label="Home" />
                <q-tab name="search" icon="search" label="Search" />
                <q-tab name="cookbook" icon="book" label="Cookbooks" />
              </q-tabs>
            </div>

            <div>
              <q-btn label="Log out" flat color="primary" />
            </div>
          </div>
        </q-toolbar>
      </q-header>

      <q-page-container class="column full-height">
        <q-tab-panels keep-alive animated v-model="tab">
          <q-tab-panel name="home">
            <PageIndex />
          </q-tab-panel>

          <q-tab-panel name="search">
            <PageIndex />
          </q-tab-panel>

          <q-tab-panel name="book">
            <CookBooks />
          </q-tab-panel>
        </q-tab-panels>
      </q-page-container>
    </div>

    <!-- <q-img src="../assets/cookbooks-img.jpg" class="top" /> -->
  </q-layout>
</template>

<script>
import PageIndex from "../pages/Index";
import CookBooks from "../pages/Cookbooks";
export default {
  name: "MainLayout",
  components: {
    PageIndex,
    CookBooks,
  },
  data() {
    return {
      tab: "home",
    };
  },
  methods: {
    reach(path) {
      if (this.$router.currentRoute.fullPath !== path) this.$router.push(path);
    },
  },
};
</script>

<style>
.bg-image {
  height: 100%;
  z-index: -1;
  background-image: url("/src/assets/cookbooks-img.jpg");
  background-repeat: no-repeat;
  background-size: contain;
}
.text-h4 {
  text-shadow: 2px 2px rgb(49, 49, 49);
}

.header-bar {
  background: transparent !important;
  /* border-bottom: 1px solid white; */
}
</style>
