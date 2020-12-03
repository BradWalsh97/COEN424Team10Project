<template>
  <div style="padding-right: 0 !important">
    <q-drawer
      :value="true"
      :overlay="true"
      side="left"
      behavior="desktop"
      class="search-drawer"
    >
      <div class="text-h5 text-capitalize search-header">Search By</div>
      <q-scroll-area style="height: calc(100% - 50px); margin-top: 50px">
        <q-tabs
          v-model="selectedSearchTab"
          vertical
          align="right"
          inline-label
          class="text-primary"
        >
          <q-tab
            name="keyword"
            icon="search"
            label="Keyword"
            @click="resetWordsAndResult"
          ></q-tab>
          <q-tab
            name="ingredients"
            icon="list"
            label="Ingredients"
            @click="resetWordsAndResult"
          ></q-tab>
          <q-tab
            name="image"
            icon="image"
            label="Image"
            @click="resetWordsAndResult"
          ></q-tab>
        </q-tabs>
      </q-scroll-area>
    </q-drawer>
    <div style="padding-left: 300px">
      <q-tab-panels
        v-model="selectedSearchTab"
        animated
        keep-alive
        transition-prev="slide-down"
        transition-next="slide-up"
      >
        <q-tab-panel name="keyword" style="padding-left: 100px">
          <div>
            <q-input
              style="
                border-radius: 5px;
                margin-right: 300px;
                margin-bottom: 2em;
              "
              bg-color="brown-5"
              color="primary"
              outlined
              v-model="keywordSearch"
              placeholder="Search for any term..."
              @keyup.enter="searchByKeyword"
            >
              <template v-slot:append>
                <q-icon name="search" />
              </template>
            </q-input>

            <div class="search-container">
              <q-card
                :key="recipe.id"
                v-for="recipe in searchRecipes"
                style="max-width: 250px"
                @click="(selectedRecipe = recipe), (showRecipe = true)"
              >
                <q-img :src="recipe.image" basic>
                  <div class="absolute-bottom text-subtitle2 text-center">
                    {{ recipe.title }}
                  </div>
                </q-img>
              </q-card>
            </div>
          </div>
        </q-tab-panel>
        <q-tab-panel name="ingredients">
          <div
            style="display: flex; justify-content: center; margin-right: 300px"
          >
            <div class="search-card">
              <div class="text-h5 text-primary" style="margin-bottom: 0.5em">
                List your ingredients here
              </div>
              <div style="display: flex; margin-bottom: 1em">
                <q-input
                  v-model="ingredients"
                  color="dark"
                  bg-color="brown-5"
                  outlined
                  type="textarea"
                  label="Ingredients you want"
                  style="flex: 1; margin-right: 0.5em"
                  class="fill-input"
                  :input-style="{ resize: 'none' }"
                  rows="3"
                />
                <q-input
                  v-model="badIngredients"
                  color="dark"
                  outlined
                  bg-color="brown-5"
                  type="textarea"
                  label="Ingredients you don't want"
                  style="flex: 1; margin-left: 0.5em"
                  class="fill-input"
                  :input-style="{ resize: 'none' }"
                  rows="3"
                />
              </div>
              <div style="text-align: right">
                <q-btn
                  color="primary"
                  outline
                  icon="search"
                  @click="searchByIngredients"
                  >Search</q-btn
                >
              </div>
            </div>
          </div>
          <div class="search-container">
            <q-card
              :key="recipe.id"
              v-for="recipe in searchRecipes"
              style="max-width: 250px"
              @click="(selectedRecipe = recipe), (showRecipe = true)"
            >
              <q-img :src="recipe.image" basic>
                <div class="absolute-bottom text-subtitle2 text-center">
                  {{ recipe.title }}
                </div>
              </q-img>
            </q-card>
          </div>
        </q-tab-panel>
        <q-tab-panel name="image">
          <div
            style="
              display: flex;
              flex-direction: column;
              align-items: center;
              margin-right: 300px;
            "
          >
            <div class="text-h2 text-primary" style="margin-bottom: 1em">
              Image Search
            </div>
            <q-uploader
              color="dark"
              :factory="searchByImage"
              label="Upload an image"
              style="margin-bottom: 2rem"
            >
              <template v-slot:header="scope">
                <div class="row no-wrap items-center q-pa-sm q-gutter-xs">
                  <q-btn
                    v-if="scope.queuedFiles.length > 0"
                    icon="clear_all"
                    @click="scope.removeQueuedFiles"
                    round
                    dense
                    flat
                  >
                    <q-tooltip>Clear All</q-tooltip>
                  </q-btn>
                  <q-btn
                    v-if="scope.uploadedFiles.length > 0"
                    icon="done_all"
                    @click="scope.removeUploadedFiles"
                    round
                    dense
                    flat
                  >
                    <q-tooltip>Remove Uploaded Files</q-tooltip>
                  </q-btn>
                  <q-spinner
                    v-if="scope.isUploading"
                    class="q-uploader__spinner"
                  />
                  <div class="col">
                    <div class="q-uploader__title">Upload your files</div>
                    <!-- <div class="q-uploader__subtitle">
                    {{ scope.uploadSizeLabel }} /
                    {{ scope.uploadProgressLabel }}
                  </div> -->
                  </div>
                  <q-btn
                    v-if="scope.canAddFiles"
                    type="a"
                    icon="add_box"
                    round
                    dense
                    flat
                  >
                    <q-uploader-add-trigger />
                    <q-tooltip>Pick Files</q-tooltip>
                  </q-btn>
                  <q-btn
                    v-if="scope.canUpload"
                    icon="cloud_upload"
                    @click="scope.upload"
                    round
                    dense
                    flat
                  >
                    <q-tooltip>Upload Files</q-tooltip>
                  </q-btn>

                  <q-btn
                    v-if="scope.isUploading"
                    icon="clear"
                    @click="scope.abort"
                    round
                    dense
                    flat
                  >
                    <q-tooltip>Abort Upload</q-tooltip>
                  </q-btn>
                </div>
              </template>
            </q-uploader>
            <template v-if="searchWords.length > 0">
              <div class="text-h6 text-primary" style="margin-bottom: 0.5rem">
                <q-icon class="text-h4" name="info"></q-icon>
                Elements from image above
              </div>
              <div>
                <q-chip
                  color="teal"
                  text-color="white"
                  :key="word"
                  v-for="word in searchWords"
                >
                  {{ word }}
                </q-chip>
              </div>
            </template>
          </div>
        </q-tab-panel>
      </q-tab-panels>

      <SeeRecipe
        v-model="showRecipe"
        v-if="selectedRecipe"
        :recipe="selectedRecipe"
      />
    </div>
  </div>
</template>

<script>
import Axios from "axios";
import QSpinnerBall from "quasar";
import SeeRecipe from "../components/SeeRecipe";

const urlSchema = require("../SizzleUrls").default;

export default {
  name: `search`,
  components: { SeeRecipe },
  data() {
    return {
      selectedSearchTab: "keyword",
      keywordSearch: "",
      searchRecipes: [],
      searchWords: [],
      selectedRecipe: null,
      showRecipe: false,
      ingredients: "",
      badIngredients: "",
    };
  },
  methods: {
    resetWordsAndResult() {
      this.searchRecipes = [];
      this.searchWords = [];
    },
    searchByKeyword() {
      return Axios.get(`${urlSchema.searchUrl}${this.keywordSearch}`).then(
        (res) => (this.searchRecipes = res.data)
      );
    },
    searchByIngredients() {
      return Axios.get(`${urlSchema.searchUrl}ingredients`, {
        params: {
          ingredients: this.ingredients,
          badIngredients: this.badIngredients,
        },
      }).then((res) => (this.searchRecipes = res.data));
    },
    searchByImage(file) {
      var image = file[0];
      var formData = new FormData();

      formData.append("file", image);
      Axios.post(`${urlSchema.imageUrl}analyseMultiFile`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      }).then((res) => this.retrieveKeywords(res.data));
    },

    retrieveKeywords(data) {
      this.searchWords = [];
      data.forEach(this.appendWord);
      if (this.searchWords.length == 0) {
        this.searchWords.push(
          "Could not analyze image with high confidence, try another"
        );
      }
    },
    appendWord(word) {
      this.searchWords.push(word.name);
    },
  },
};
</script>

<style lang="scss">
@import "../css/app.scss";

.search-drawer {
  .q-drawer {
    background-color: rgba(197, 135, 64, 0.5) !important;
    backdrop-filter: blur(5px);
    margin-top: -50px;
    padding-top: 50px;
    z-index: 0;
  }

  .search-header {
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
}

.search-container {
  display: flex;
  padding: 1em;
  flex-wrap: wrap;

  & .q-card {
    width: 300px;
    margin-right: 15px;
    margin-bottom: 15px;
  }
}

.search-card {
  @extend .bg-tint;
  padding: 1em;
  margin-bottom: 2em;
  width: 600px;
  border-radius: 3px;
}
</style>
