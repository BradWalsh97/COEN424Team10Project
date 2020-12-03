<template>
  <q-layout view="Lhh lpR fff" container :class="customBackground">
    <q-header class="bg-primary">
      <q-toolbar>
        <q-toolbar-title>Add new Recipe</q-toolbar-title>

        <q-btn flat v-close-popup round dense icon="close" />
      </q-toolbar>
    </q-header>

    <q-page-container>
      <q-page padding>
        <!-- <div>Title</div> -->
        <div class="q-pa-md q-gutter-sm">
          <q-input
            v-model="recipe.title"
            label="Title"
            bg-color="brown-5"
            outlined
          />
        </div>
        <div class="q-pa-md q-gutter-sm" style="padding-top: 1%">
          <div class="text-h5">Summary</div>
          <div>
            <q-editor
              v-model="recipe.summary"
              toolbar-bg="brown-6"
              :dense="$q.screen.lt.md"
              :toolbar="[
                ['undo', 'redo'],
                [
                  {
                    label: $q.lang.editor.align,
                    icon: $q.iconSet.editor.align,
                    fixedLabel: true,
                    list: 'only-icons',
                    options: ['left', 'center', 'right', 'justify'],
                  },

                  'bold',
                  'italic',
                  'strike',
                  'underline',

                  'link',
                ],

                // ['viewsource'],
              ]"
              :fonts="{
                arial: 'Arial',
                arial_black: 'Arial Black',
                comic_sans: 'Comic Sans MS',
                courier_new: 'Courier New',
                impact: 'Impact',
                lucida_grande: 'Lucida Grande',
                times_new_roman: 'Times New Roman',
                verdana: 'Verdana',
              }"
            />
          </div>
        </div>

        <div class="q-pa-md q-gutter-sm" style="padding-top: 1%">
          <div class="text-h5">Instructions</div>
          <div>
            <q-editor
              v-model="recipe.instructions"
              :dense="$q.screen.lt.md"
              toolbar-bg="brown-6"
              :toolbar="[
                ['undo', 'redo'],
                [
                  {
                    label: $q.lang.editor.align,
                    icon: $q.iconSet.editor.align,
                    fixedLabel: true,
                    list: 'only-icons',
                    options: ['left', 'center', 'right', 'justify'],
                  },

                  'bold',
                  'italic',
                  'strike',
                  'underline',
                  'link',
                  'unordered',
                  'ordered',
                ],

                // ['viewsource'],
              ]"
            />
          </div>
        </div>

        <div class="q-pa-md q-gutter-none bg-tint" style="padding-top: 2%">
          <q-file
            v-model="recipe.image"
            label="Image"
            outlined
            counter
            clearable
          >
            <template v-slot:prepend>
              <q-icon name="attach_file" />
            </template>
          </q-file>
        </div>
        <div class="q-pa-md bg-tint" style="">
          <div>
            Your recipe's privacy:
            <q-radio v-model="recipe.isPublic" :val="true" label="Public" />
            <q-radio v-model="recipe.isPublic" :val="false" label="Private" />
          </div>
        </div>
        <q-btn color="primary" outline label="Save" @click="showSelectCookbook = true" style="margin-top: 2em; padding: 0 2em;" />

        <SelectCookBook :recipe="recipe" :adding="true" v-model="showSelectCookbook" v-on:closedialog="closeDialog" />
        
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script>
import Axios from "axios";
import SelectCookBook from "./SelectCookBook";
const urlSchema = require("../SizzleUrls").default;


export default {
  name: "AddNewRecipe",
  components: { SelectCookBook },
  data() {
    return {
      showSelectCookbook: false,
      recipe: {
        title: "",
        summary: "",
        instructions: "",
        image: null,
        isPublic: true,
      },
      user: "",
      cookBook: -1,
      cookBooks: {},
    };
  },
  computed: {
    customBackground: function () {
      if (this.$q.dark.isActive) {
        return "bg-grey-10";
      } else {
        return "bg-white";
      }
    },
  },
  methods: {
    addNewRecipe: function () {},
    closeDialog() {
      console.log('closing cookbook selector');
      this.showSelectCookbook = false;
      this.$emit('closedialog');
    }
  },
  mounted() {
    this.user = this.$store.getters["example/getUser"];
    console.log('user', this.user);
  },
  // watch: {
  // 	this.$q.dark.isActive() :
  // },
};
</script>

<style></style>
