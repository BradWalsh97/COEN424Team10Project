<template>
  <!-- <div> -->
  <q-card class="my-card">
    <q-img :src="recipe.image" />
    <q-separator />
    <q-card-section>
      <q-btn
        fab
        color="primary"
        icon="bookmark"
        class="absolute"
        style="top: 0; right: 12px; transform: translateY(-50%)"
      />

      <div class="row no-wrap items-center">
        <div class="col text-h6 ellipsis">
          {{ recipe.title }}
        </div>
        <div
          class="col-auto text-dark-grey text-caption q-pt-md row no-wrap items-center"
        ></div>
      </div>
    </q-card-section>

    <q-card-section class="q-pt-none">
      <div class="text-subtitle1" v-html="recipe.summary"></div>
    </q-card-section>

    <q-card-section>
      Instructions
      <div class="text-grey" v-html="recipe.instructions"></div>
    </q-card-section>
  </q-card>
  <!-- </div> -->
</template>

<script>
const urlSchema = require("../SizzleUrls").default;

export default {
  name: "SeeRecipe",
  // data() {},
  props: {
    recipe: {
      image: "",
      title: "",
      summary: "",
      instructions: "",
    },
  },
};
</script>

<style scoped></style>
