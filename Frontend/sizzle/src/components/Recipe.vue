<template>
  <q-card style="height: 50%; width: 50%">
    <q-card-section>
      <div class="text-h3">
        {{ aRecipe.title }}
      </div>
    </q-card-section>

    <q-card-section>
      <q-img :src="aRecipe.image" style="height: 50%; width: 50%" />
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
      required: true
    }
  }
};
</script>
