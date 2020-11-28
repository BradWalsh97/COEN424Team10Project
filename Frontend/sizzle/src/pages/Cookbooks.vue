<template>
  <q-page-container style="padding-right: 0 !important">
    <q-drawer
      show-if-above
      v-model="right"
      side="right"
      behavior="desktop"
      class="cookbook-drawer"
    >
      <div class="text-h5 text-capitalize cookbook-header">
        Your cookbooks
        <q-btn class="cookbook-add" flat round icon="add"> </q-btn>
      </div>
      <q-scroll-area style="height: calc(100% - 50px); margin-top: 50px">
        <q-list style="padding: 0 1rem">
          <q-card
            v-for="index in (0, 10)"
            :key="index"
            class="my-card text-white"
            style="
              background: radial-gradient(circle, #35a2ff 0%, #014a88 100%);
              margin: 0.8rem 0;
            "
          >
            <q-card-section>
              <div class="text-h6">Our Changing Planet</div>
              <div class="text-subtitle2">by John Doe</div>
            </q-card-section>
          </q-card>
        </q-list>
      </q-scroll-area>
    </q-drawer>

    <div class="col-1 q-pb-md">
      <div class="column items-center text-h2">"Current Cookbook Name"</div>
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
  </q-page-container>
</template>

<script>
const urlSchema = require("../SizzleUrls").default;
export default {
  data() {
    return {
      right: true,
    };
  },
  beforeMount() {
    console.log("Before mount CookBook");
    if (!this.$store.getters["example/isAuthenticated"]) {
      this.$router.push("/login");
    }
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
    background-color: rgba(255, 224, 188, 0.5);
    backdrop-filter: blur(5px);
  }

  .cookbook-header {
    position: absolute;
    top: 0;
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
