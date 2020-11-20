<template>
  <q-layout view="hhr lpR fFr">

    <!-- <q-header reveal elevated class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo/svg/quasar-logo.svg">
          </q-avatar>
          Sizzle
        </q-toolbar-title>

        <q-btn dense flat round icon="menu" @click="right = !right" />
      </q-toolbar>

      
    </q-header> -->

    <q-drawer show-if-above v-model="right" side="right" behavior="desktop" elevated>
        <q-scroll-area style="height: calc(100% - 200px); margin-top: 200px; border-right: 1px solid #ddd">
          <q-list padding>
            <q-item clickable v-ripple>
              <q-item-section avatar>
                <q-icon name="book" />
              </q-item-section>

              <q-item-section>
                Inbox
              </q-item-section>
            </q-item>

            
          </q-list>
        </q-scroll-area>

        <q-img class="dw-img absolute-top" src="../assets/cookbook-tabs.jpg" style="height: 200px">
          <div class="absolute-bottom bg-transparent">
            <div class="text">Your Library of Cookbooks:</div>
        
          </div>
        </q-img>

      <!-- drawer content -->
    </q-drawer>

    <q-page-container>
     

        <div class="col-1 q-pb-md">
            <div class="column items-center text-h2">"Current Cookbook Name"</div>

        </div>

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

                <!-- </div> -->
                <!-- </div> -->
            </q-carousel>

          
        </div>
           
        
    </q-page-container>

    
      <router-view />
  </q-layout>
</template>

<script>
export default {
  data () {
    return {
      baseUrl: "http://localhost:8181/cookbooks/"
    }
  }
}
</script>

<style lang="scss">

    .text-h2{
       color: rgb(255, 255, 255); 
    }

    .text-h3{
        color: black;
    }

  

  .dw-img{
    z-index: -1;  
  }




</style>