<template>
  <div id="q-app">
    <router-view />
  </div>
</template>
<script>
export default {
  name: 'App'
}
</script>
<style lang="scss">
.q-layout {
    background-image: url("./assets/granite-top.jpeg");
    background-repeat: no-repeat;
    background-size: cover;
}
</style>