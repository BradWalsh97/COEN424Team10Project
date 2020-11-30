<template>
  <div style="padding-right: 0 !important">
    <q-drawer
      show-if-above
      v-model="right"
      overlay
      side="left"
      behavior="desktop"
      class="cookbook-drawer"
    >
      <div class="text-h5 text-capitalize cookbook-header">
        Your cookbooks <q-btn class="cookbook-add" flat round icon="add" @click="showAddCookbook = true"></q-btn>
      </div>
      <q-scroll-area style="height: calc(100% - 50px); margin-top: 50px">
         <q-tabs
            :value="selectedCookbookTab"
            vertical
            align="right"
            inline-label
            class="text-primary">
            <q-tab :key="cookbook.id" v-for="cookbook in userCookbooks" 
              icon="book" :name="`cookbook${cookbook.id}`" :label="cookbook.name" @click="selectedCookbook = cookbook"></q-tab>
          </q-tabs>
      </q-scroll-area>
    </q-drawer>

    <div class="col-1 q-pb-md">
      <div class="column items-center text-h2">{{selectedCookbook ? selectedCookbook.name : 'Add Cookbook to Start'}}</div>
    </div>

    <div class="column items-center" style="height: 500pt; width: 100%">
      <!-- <q-carousel
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
                style="width: 70%; height: 100%"
            >
                <q-carousel-slide
                    v-for="recipe in recipes"
                    :key="recipe.id"
                    :name="recipe.id"
                    :img-src="recipe.image"
                    @click="test(recipe.id)"
                >
                </q-carousel-slide>

            </q-carousel> -->
    </div>

    <AddCookBook v-model="showAddCookbook"/>
    
  </div>
</template>

<script>
import AddCookBook from '../components/AddCookBook';
import Axios from 'axios';
const urlSchema = require("../SizzleUrls").default;
export default {
  components: { AddCookBook },
  data() {
    return {
      selectedCookbook: null,
      right: true,
      showAddCookbook: false,
      userCookbooks: []
    };
  },
  beforeMount() {
    console.log("Before mount CookBook");
    if (!this.$store.getters["example/isAuthenticated"]) {
      this.$router.push("/login");
    }
  },
  activated() {
    this.getCookbooks().then(_ => {
      if (this.userCookbooks.length > 0)
        this.selectedCookbook = this.userCookbooks[0];
    });
  },
  computed: {
    selectedCookbookTab() {
      if (this.selectedCookbook)
        return `cookbook${this.selectedCookbook.id}`;
      return null;
    }
  },
  methods: {
    getCookbooks() {
      return Axios.get(`${urlSchema.profileUrl}getCookBooksFor/${this.$store.getters["example/getUser"]}`)
      .then(res => this.userCookbooks = res.data.cookBooks);
    },
  }
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
}

.dw-img {
  z-index: -1;
}
</style>
