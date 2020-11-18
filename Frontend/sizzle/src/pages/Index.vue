<template>
  <q-page class="full-width">
    <!-- <div class="col " style="padding-left: 30%"> -->
    <div class="q-pa-md doc-container">
      <div class="column items-center text-h2">Random Recipes</div>
      <div class="" >
        <q-btn label="Refresh" @click="getRecipes()" style="width: 100px" />
      </div>
      <!-- <div class="q-pa-md"> -->
      <!-- <div class="col-6 justify-center" style="width: 100%"> -->
      <div class="column items-center">
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
          style="width: 60%; height: 500pt"
        >
          <q-carousel-slide
            v-for="recipe in recipes"
            :key="recipe.id"
            :name="recipe.id"
            :img-src="recipe.image"
          >
          </q-carousel-slide>

          <!-- </div> -->
          <!-- </div> -->
        </q-carousel>
      </div>
    </div>
  </q-page>
</template>

<script>
import Axios from "axios";
// import Recipe from "../components/Recipe.vue";

export default {
  name: "PageIndex",
  components: {
    // Recipe,
  },
  data() {
    return {
      baseUrl: "http://localhost:8181/api/recipes/get",
      recipes: {},
      slide: 0,
    };
  },
  methods: {
    getRecipes() {
      Axios.get(this.baseUrl)
        .then((res) => {
          this.recipes = res.data;
          // console.log("res", res);
          console.log("res.data", res.data);
        })
        .catch((err) => console.log("error during get", err));
    },
  },
  mounted() {
    this.getRecipes();
  },
};
</script>
