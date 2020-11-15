<template>
  <q-page class="row justify-center">
    <!-- <div class="col " style="padding-left: 30%"> -->
    <div class="">
      <div class="text-h1">Random Recipes</div>
      <q-btn label="Refresh" @click="getRecipes()" style="width: 100px" />

      <div class="row">
        <div v-for="recipe in recipes" :key="recipe.image">
          <div style="padding-top: 1%; width: 50%">
            <Recipe v-bind:aRecipe="recipe" />
          </div>
        </div>
      </div>
    </div>
  </q-page>
</template>

<script>
import Axios from "axios";
import Recipe from "../components/Recipe.vue";

export default {
  name: "PageIndex",
  components: {
    Recipe,
  },
  data() {
    return {
      baseUrl: "http://localhost:8181/api/recipes/random/",
      recipes: {},
    };
  },
  methods: {
    getRecipes() {
      Axios.get(this.baseUrl + 5)
        .then((res) => (this.recipes = res.data))
        .catch((err) => console.log("error during get", err));
    },
  },
};
</script>
