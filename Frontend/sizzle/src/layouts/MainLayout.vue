<template>
  <q-layout view="lHh Lpr lFf">
    <q-header>
      <q-toolbar class="tb text-primary bg-X" >
        <div class="full-width row justify-between">
        <div>
        <q-toolbar-title> Sizzle </q-toolbar-title>
        </div>
        <div>
            <q-tabs
            dense inline-label
        v-model="tab"
        class="float-left"
      >
        <q-tab name="search" icon="search" label="Search" />
        <q-tab name="home" icon="home" label="Home" />
        <q-tab name="cookbooks" icon="book" label="Cookbooks" />
      </q-tabs>
        </div>

  <div>
            <q-btn label="Log out" flat />
          </div>
        </div>
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>

    <q-img 
        src="../assets/cookbooks-img.jpg"
        class="bg-image absolute-top"/>
  </q-layout>
</template>

<script>
export default {
  name: "MainLayout",

  data() {
    return {};
  },
};
</script>

<style>
  .bg-image {
    height: 100%;
    z-index: -1;
    background-image: url('/src/assets/cookbooks-img.jpg');
    background-repeat: no-repeat;
    background-size: contain;
  }

  .tb{
    background-color: rgba(105, 105, 105);
  }
 
</style>


