<template>
  <q-layout view="Lhh lpR fff" container class="bg-white">
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
          <q-input v-model="recipe.title" label="Title" outlined />
        </div>
        <div class="q-pa-md q-gutter-sm" style="padding-top: 1%">
          <div class="text-h5">Summary</div>
          <div>
            <q-editor
              v-model="recipe.summary"
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

                  {
                    label: $q.lang.editor.fontSize,
                    icon: $q.iconSet.editor.fontSize,
                    fixedLabel: true,
                    fixedIcon: true,
                    list: 'no-icons',
                    options: [
                      'size-1',
                      'size-2',
                      'size-3',
                      'size-4',
                      'size-5',
                      'size-6',
                      'size-7',
                    ],
                  },

                  'unordered',
                  'ordered',
                ],

                // ['viewsource'],
              ]"
            />
          </div>
        </div>

        <div class="q-pa-md q-gutter-sm" style="padding-top: 1%">
          <q-btn label="Save" outline color="primary" />
        </div>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: "AddNewRecipe",
  data() {
    return {
      recipe: {
        title: "",
        summary: "",
        instructions: "",
      },
    };
  },
};
</script>

<style scoped></style>
