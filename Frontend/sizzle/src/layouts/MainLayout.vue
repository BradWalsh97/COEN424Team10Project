<template>
  <q-layout view="lHh Lpr lFf">
    <q-header class="header-bar">
      <q-toolbar class="tb text-white bold" >
        <div class="full-width row justify-between">
        <div>
        <q-toolbar-title class="text-h4"> Sizzle </q-toolbar-title>
        </div>
        <div style="display: flex; justify-content: center; align-items: center;">
          <q-btn unelevated name="search" icon="search" label="Search" />
          <q-btn unelevated name="home" icon="home" label="Home" to="/" clickable v-ripple />
          <q-btn unelevated name="cookbooks" icon="book" label="Cookbooks" to="/cookbooks" clickable v-ripple />
        </div>

  <div>
            <q-btn label="Log out" flat color="white" />
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
    return {
      tab: ''
    };
  },
  methods: {
    reach(path) {
      if (this.$router.currentRoute.fullPath !== path)
        this.$router.push(path);
    }
  }
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
  .text-h4{
    text-shadow: 2px 2px rgb(49, 49, 49);
  }

  .header-bar {
    background: transparent !important;
    border-bottom: 1px solid white;
  }
  

 
</style>


