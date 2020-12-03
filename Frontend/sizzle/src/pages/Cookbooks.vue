<template>
  <div style="padding-right: 0 !important">
    <q-drawer
      show-if-above
      :value="true"
      :overlay="true"
      side="left"
      behavior="desktop"
      class="cookbook-drawer"
    >
      <div class="text-h5 text-capitalize cookbook-header">
        Your cookbooks
        <q-btn
          class="cookbook-add"
          flat
          round
          icon="add"
          @click="showAddCookbook = true"
        ></q-btn>
      </div>
      <q-scroll-area style="height: calc(100% - 50px); margin-top: 50px">
        <q-tabs
          :value="selectedCookbookTab"
          vertical
          align="left"
          inline-label
          class="text-primary"
        >
          <q-tab
            :key="cookbook.id"
            v-for="cookbook in userCookbooks"
            icon="book"
            :name="`cookbook${cookbook.id}`"
            :label="cookbook.name"
            @click="selectCookbook(cookbook)"
          >
            <q-icon
              color="red"
              name="delete"
              class="cookbook-delete"
              @click.stop="
                (deleteCookbook = cookbook), (showConfirmCookbook = true)
              "
            />
          </q-tab>
        </q-tabs>
      </q-scroll-area>
    </q-drawer>
    <div style="padding-left: 300px">
      <div class="col-1 q-pb-md">
        <div class="row justify-center text-h2" style="padding-right: 300px">
          <span class="bg-tint rounded-borders">
            {{
              selectedCookbook ? selectedCookbook.name : "Add Cookbook to Start"
            }}</span
          >
        </div>
      </div>

      <div class="row" style="height: 500pt; width: 100%">
        <q-card
          :key="recipe.id"
          v-for="recipe in selectedCookbookRecipes"
          style="width: 300px; margin-right: 15px; margin-bottom: 15px"
          @click="(selectedRecipe = recipe), (showRecipe = true)"
        >
          <q-img :src="recipe.image" basic>
            <div class="absolute-bottom text-subtitle2 text-center">
              {{ recipe.title }}
            </div>
          </q-img>
          <q-btn
            flat
            round
            color="negative"
            icon="delete"
            class="card-btn"
            @click.stop="(deleteRecipe = recipe), (showConfirmRecipe = true)"
          />
        </q-card>
      </div>

      <AddCookBook v-model="showAddCookbook" @refresh="getCookbooks" />

      <SeeRecipe
        v-model="showRecipe"
        v-if="selectedRecipe"
        :recipe="selectedRecipe"
      />

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
        <AddNewRecipe v-on:closedialog="layout = !layout" />
      </q-dialog>

      <q-dialog v-model="showConfirmRecipe" persistent>
        <q-card>
          <q-card-section class="row items-center">
            <q-avatar icon="delete" color="negative" text-color="white" />
            <span class="q-ml-sm"
              >Are you sure you want to delete this recipe?</span
            >
          </q-card-section>

          <q-card-actions align="right">
            <q-btn flat label="Cancel" color="grey" v-close-popup />
            <q-btn
              flat
              label="Delete"
              color="negative"
              @click="removeRecipeFromCookbook"
            />
          </q-card-actions>
        </q-card>
      </q-dialog>

      <q-dialog v-model="showConfirmCookbook" persistent>
        <q-card>
          <q-card-section class="row items-center">
            <q-avatar icon="delete" color="negative" text-color="white" />
            <span class="q-ml-sm"
              >Are you sure you want to delete this cookbook?</span
            >
          </q-card-section>

          <q-card-actions align="right">
            <q-btn flat label="Cancel" color="grey" v-close-popup />
            <q-btn
              flat
              label="Delete"
              color="negative"
              @click="removeCookbookFromAccount"
            />
          </q-card-actions>
        </q-card>
      </q-dialog>
    </div>
  </div>
</template>

<script>
import AddCookBook from "../components/AddCookBook";
import SeeRecipe from "../components/SeeRecipe";
import AddNewRecipe from "../components/AddNewReceipt";
import Axios from "axios";
const urlSchema = require("../SizzleUrls").default;
export default {
  components: { AddCookBook, SeeRecipe, AddNewRecipe },
  data() {
    return {
      selectedCookbook: null,
      selectedCookbookRecipes: [],
      selectedRecipe: null,
      showRecipe: false,
      showConfirmRecipe: false,
      showConfirmCookbook: false,
      showAddCookbook: false,
      userCookbooks: [],
      deleteRecipe: null,
      deleteCookbook: null,
      layout: false,
    };
  },
  beforeMount() {
    console.log("Before mount CookBook");
    if (!this.$store.getters["example/isAuthenticated"]) {
      this.$router.push("/login");
    }
  },
  activated() {
    this.getCookbooks().then((_) => {
      if (this.userCookbooks.length > 0)
        this.selectCookbook(this.userCookbooks[0]);
    });
  },
  computed: {
    selectedCookbookTab() {
      if (this.selectedCookbook) return `cookbook${this.selectedCookbook.id}`;
      return null;
    },
  },
  methods: {
    getCookbooks() {
      return Axios.get(
        `${urlSchema.profileUrl}getCookBooksFor/${this.$store.getters["example/getUser"]}`
      ).then((res) => (this.userCookbooks = res.data.cookBooks));
    },
    getCookbookRecipes() {
      return Axios.get(
        `${urlSchema.recipeUrl}cookbook/${this.$store.getters["example/getUser"]}/${this.selectedCookbook.id}`
      ).then((res) => (this.selectedCookbookRecipes = res.data));
    },
    selectCookbook(cookbook) {
      console.log("clicked on tab", cookbook.id);
      this.selectedCookbook = cookbook;
      this.getCookbookRecipes();
    },
    removeRecipeFromCookbook() {
      return Axios.delete(
        `${urlSchema.profileUrl}removeRecipeFromCookBook/${this.$store.getters["example/getUser"]}/${this.selectedCookbook.id}/${this.deleteRecipe.id}`
      )
        .finally((_) => (this.showConfirm = false))
        .then(this.getCookbookRecipes);
    },
    removeCookbookFromAccount() {
      return Axios.delete(
        `${urlSchema.profileUrl}cookBook/${this.$store.getters["example/getUser"]}/${this.deleteCookbook.id}`
      )
        .finally((_) => (this.showConfirmCookbook = false))
        .then((_) => {
          this.getCookbooks().then((_) => {
            if (this.selectedCookbook.id === this.deleteCookbook.id) {
              this.selectedCookbookRecipes = [];
            }
            if (this.userCookbooks.length > 0)
              this.selectCookbook(this.userCookbooks[0]);
          });
        });
    },
  },
};
</script>

<style lang="scss">
.text-h2 {
  color: rgb(255, 255, 255);
}

.text-h3 {
  color: black;
}

.cookbook-drawer {
  .q-drawer {
    background-color: rgba(197, 135, 64, 0.5);
    backdrop-filter: blur(5px);
    margin-top: -50px;
    padding-top: 50px;
    z-index: 0;
  }

  .cookbook-header {
    position: absolute;
    top: 50px;
    left: 0;
    right: 0;
    padding: 0 1rem;
    height: 50px;
    display: flex;
    justify-content: flex-start;
    align-items: center;
    color: white !important;
    font-weight: 400;
  }

  .cookbook-add {
    position: absolute;
    right: 0;
    margin-right: 0.3rem;
  }

  .cookbook-delete {
    font-size: 16pt;
    opacity: 0.8;
    transition: 0.2s;
    padding: 5px;
    &:hover {
      opacity: 1;
    }
  }
}

.card-btn {
  position: absolute;
  top: 5px;
  right: 5px;
}

.dw-img {
  z-index: -1;
}
</style>
