<template>
  <q-card style="">
    <q-card-section>
      <div class="text-h3">
        {{ aRecipe.title }}
      </div>
    </q-card-section>

    <q-card-section>
      <q-img :src="aRecipe.image" />
    </q-card-section>

    <q-card-section>
      <div v-html="aRecipe.summary" />
    </q-card-section>
  </q-card>
</template>

<script>
export default {
  name: "Recipe",
  props: {
    aRecipe: {
      required: true,
    },
  },
};
</script>
