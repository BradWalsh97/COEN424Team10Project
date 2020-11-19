<template>
  <q-page class="full-width column">
    <!-- <div class="col " style="padding-left: 30%"> -->
    <div class="q-pa-md doc-container">
      <div>
        <div class="col-1">
          <div class="column items-center text-h2">Random Recipes</div>
          <div class="">
            <q-btn label="Refresh" @click="getRecipes()" style="width: 100px" />
          </div>
        </div>
        <!-- <div class="q-pa-md"> -->
        <!-- <div class="col-6 justify-center" style="width: 100%"> -->
        <div class="column items-center" style="height: 500pt; width: 100%">
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
            style="width: 60%; height: 100%"
          >
            <q-carousel-slide
              v-for="recipe in recipes"
              :key="recipe.id"
              :name="recipe.id"
              :img-src="recipe.image"
              @click="test(recipe.id)"
            >
            </q-carousel-slide>

            <!-- </div> -->
            <!-- </div> -->
          </q-carousel>
        </div>
      </div>
    </div>

    <q-dialog v-model="card">
      <q-card class="my-card">
        <q-img :src="chosenRecipe.image" />

        <q-card-section>
          <q-btn
            fab
            color="primary"
            icon="save"
            class="absolute"
            style="top: 0; right: 12px; transform: translateY(-50%)"
          />

          <div class="row no-wrap items-center">
            <div class="col text-h6 ellipsis">
              {{ chosenRecipe.title }}
            </div>
            <div
              class="col-auto text-grey text-caption q-pt-md row no-wrap items-center"
            ></div>
          </div>

          <q-rating v-model="stars" :max="5" size="32px" />
        </q-card-section>

        <q-card-section class="q-pt-none">
          <div class="text-subtitle1" v-html="chosenRecipe.summary"></div>
          <div class="text-grey" v-html="chosenRecipe.instructions"></div>
        </q-card-section>

        <q-separator />

        <q-card-actions align="right">
          <q-btn v-close-popup flat color="primary" label="Reserve" />
          <q-btn v-close-popup flat color="primary" round icon="event" />
        </q-card-actions>
      </q-card>
    </q-dialog>
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
      card: false,
      chosenRecipe: {},
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
    test(id) {
      console.log("test", id);
      this.chosenRecipe = this.recipes[id];
      this.card = true;
    },
  },
  mounted() {
    this.getRecipes();
  },
};
</script>
