<template>
  <q-layout view="lHh Lpr lFf">
    <q-header>
      <q-toolbar class="text-primary bg-white">
        <q-toolbar-title> Sizzle </q-toolbar-title>

        <!-- <q-btn label="Log out" flat /> -->
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: "OutsideLayout",

  data() {
    return {};
  },
};
</script>
