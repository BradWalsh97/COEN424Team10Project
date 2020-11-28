<template>
  <q-layout view="lHh Lpr lFf">
    <q-header style="background: transparent">
      <q-toolbar class="text-primary">
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
