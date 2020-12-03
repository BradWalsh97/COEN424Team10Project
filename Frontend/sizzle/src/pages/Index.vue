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
  <!-- <q-page class="full-height"> -->
  <div>
    <q-drawer
      :value="false"
      side="left"
      :overlay="true"
      behavior="mobile"
      class="search-drawer"
    />

    <div class="column" style="height: 100%">
      <!-- <div> -->
      <div class="col-1" style="min-height: 150px">
        <div
          class="row justify-center text-h3 text-primary"
          style="padding-top: 1%"
        >
        <span class="bg-tint rounded-borders" style="padding: 20px;">Recipes you might like</span>
        </div>
        <div style="padding-top: 1%; padding-bottom: 1%"></div>
      </div>

      <div class="col-10">
        <div class="column items-center" style="width: 100%; height: 100%">
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
            class="bg-white text-white shadow-4 rounded-borders"
            style="width: 60%; height: 100%; border: 10px solid #ccc;"
          >
            <q-carousel-slide
              v-for="(recipe, index) in recipes"
              :key="index"
              :name="index"
              :img-src="recipe.image"
              @click="test(index)"
              :style="customHeight"
            />
          </q-carousel>
          <q-btn class="bg-tint" color="primary" outline label="Refresh" @click="getRecipes()" style="margin-top: 2em; padding: 0 2em;" />
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
      <AddNewRecipe v-on:closedialog="layout = !layout"/>
    </q-dialog>

    <SeeRecipe v-model="card" v-on:closeseerecipe="card = false" :recipe="chosenRecipe" />
    <!-- </q-page> -->
  </div>
</template>

<script>
import Axios from "axios";
import AddNewRecipe from "../components/AddNewReceipt";
import SeeRecipe from "../components/SeeRecipe";
const urlSchema = require("../SizzleUrls").default;

export default {
  name: "PageIndex",
  components: {
    AddNewRecipe,
    SeeRecipe,
  },
  data() {
    return {
      recipes: {},
      slide: 0,
      user: "",
      card: false,
      layout: false,
      chosenRecipe: {},
    };
  },
  methods: {
    getRecipes() {
      Axios.get(`${urlSchema.recipeUrl}random/10`)
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
    }
  },

  computed: {
    customHeight: function () {
      let h = window.innerHeight * 0.5;
      return { height: `${h}pt` };
    },
  },

  beforeMount() {
    console.log("Before mount index");
    if (!this.$store.getters["example/isAuthenticated"]) {
      this.$router.push("/login");
    } else {
      this.getRecipes();
      this.user = this.$store.getters["example/getUser"];
    }
  },
};
</script>
