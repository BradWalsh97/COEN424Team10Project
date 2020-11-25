<!-- <style lang="scss">
.bg-image {
  height: 100%;
  z-index: -1;
  background-image: url("/src/assets/cookbooks-img.jpg");
  background-repeat: no-repeat;
  background-size: contain;
}
</style> -->

<template>
  <q-page class="full-height">
    <div class="column" style="height: 100%">
      <!-- <div> -->
      <div class="col-1" style="min-height: 150px">
        <div class="column items-center text-h2 text-primary">
          Recipes you might like
        </div>
        <div style="padding-top: 1%; padding-bottom: 1%">
          <q-btn label="Refresh" @click="getRecipes()" style="width: 100px" />
        </div>
      </div>

      <div class="col-9 column-grow">
        <div
          class="column items-center full-height"
          style="width: 100%; height: 100%"
        >
          <q-carousel
            v-model="slide"
            vertical
            transition-prev="slide-down"
            transition-next="slide-up"
            swipeable
            animated
            control-color="white"
            navigation-icon="radio_button_unchecked"
            navigation
            padding
            arrows
            class="bg-purple text-white shadow-1 rounded-borders"
            style="width: 60%; height: 100%; min-height: 100%"
          >
            <q-carousel-slide
              v-for="(recipe, index) in recipes"
              :key="index"
              :name="index"
              :img-src="recipe.image"
              @click="test(index)"
              style="min-height: 40em; overflow: auto"
            />
          </q-carousel>
        </div>
      </div>
      <!-- </div> -->
    </div>

    <q-page-sticky position="bottom-right" :offset="[40, 40]">
      <q-fab
        icon="add"
        :value="layout"
        color="accent"
        @click="layout = !layout"
      />
    </q-page-sticky>

    <q-dialog
      v-model="layout"
      persistent
      transition-show="scale"
      transition-hide="scale"
    >
      <AddNewRecipe />
    </q-dialog>

    <q-dialog v-model="card">
      <SeeRecipe v-bind:recipe="chosenRecipe" />
    </q-dialog>
  </q-page>
</template>

<script>
import Axios from "axios";
import AddNewRecipe from "../components/AddNewReceipt";
import SeeRecipe from "../components/SeeRecipe";
//import baseURL from "url";
let urlSchema = require("url");

export default {
  name: "PageIndex",
  components: {
    AddNewRecipe,
    SeeRecipe,
  },
  data() {
    return {
      //baseUrl: urlSchema.localURL + "/recipes/get/",
      baseUrl: "http://localhost/api/recipes/get/",
      recipes: {},
      slide: 0,
      card: false,
      layout: false,
      chosenRecipe: {},
    };
  },
  methods: {
    getRecipes() {
      console.log("urlSchema: ", this.urlSchema);
      console.log("BaseURL: ", this.baseURL);
      console.log("BaseUrl: ", this.baseUrl);
      Axios.get(this.baseUrl)
        .then((res) => {
          this.recipes = res.data;
          console.log("res.data", res.data);
        })
        .catch((err) => console.log("error during get", err));
    },
    test(id) {
      console.log("test", id);
      this.chosenRecipe = this.recipes[id];
      this.card = true;
    },
  },
  beforeMount() {
    this.getRecipes();
  },
};
</script>
