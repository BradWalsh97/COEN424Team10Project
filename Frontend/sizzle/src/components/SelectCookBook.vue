<template>
  <q-dialog :value="value" persistent @hide="$emit('input', false)">
    <q-card style="min-width: 350px">
      <q-card-section>
        <div class="text-h6">Save To CookBook</div>
        <div class="text-subtitle1">
          Select which cookbook you would like to add this recipe to
        </div>
      </q-card-section>

      <q-card-section class="q-pt-none">
        <q-select
          color="accent"
          dense
          v-model="cookbook"
          label="Cookbook"
          :options="cookbookList"
        >
        </q-select>

        <q-input
          color="accent"
          dense
          v-model="newCookbookName"
          label="New Cookbook Name"
          style="margin-top: 1rem"
          placeholder="Enter the name of the new cookbook"
          :rules="[(v) => !!v || 'Required']"
          v-if="!cookbook.value"
        />
      </q-card-section>

      <q-card-actions align="right" class="text-primary">
        <q-btn color="accent" flat label="Cancel" v-close-popup />
        <q-btn color="accent" flat label="Save" @click="save" />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script>
import Axios from "axios";
const urlSchema = require("../SizzleUrls").default;
export default {
  name: "add-cookbook",
  props: {
    value: {
      type: Boolean,
      required: true,
    },
    recipe: {
      type: Object,
      required: true,
    }
  },
  data() {
    return {
      userCookbooks: [],
      cookbook: { label: "New cookbook", value: null },
      newCookbookName: "",
      newCookbook: undefined
    };
  },
  computed: {
    cookbookList() {
      return [
        { label: "New cookbook", value: null },
        ...this.userCookbooks.map((c) => ({ label: c.name, value: c })),
      ];
    },
  },
  mounted() {
    this.getCookbooks();
  },
  methods: {
    save() {
      console.log(this.recipe);
      console.log(this.cookbook);
      if (this.recipe.fromCookbook) {
        this.saveToCookbook();
      }
      else {
        this.addRecipe();
      }
    },

    getCookbooks() {
      Axios.get(
        `${urlSchema.profileUrl}getCookBooksFor/${this.$store.getters["example/getUser"]}`
      ).then((res) => (this.userCookbooks = res.data.cookBooks));
    },
    async saveToCookbook() {
      console.log("Saving recipe to db and image to s3");

      var bodyFormData = new FormData();
      bodyFormData.append("title", this.recipe.title);
      bodyFormData.append("summary", this.recipe.summary);
      bodyFormData.append("instructions", this.recipe.instructions);
      bodyFormData.append("imageFile", this.recipe.image);
      bodyFormData.append("isPublic", this.recipe.isPublic);

      var that = this;

      var body = { ...this.recipe };
      if (this.cookbook.value) {
        Axios({
          method: "put",
          url: `${urlSchema.recipeUrl}newRecipe/${this.$store.getters["example/getUser"]}/${this.cookbook.value.id}`,
          data: bodyFormData,
          headers: { "Content-Type": "multipart/form-data" },
        }).then((res) => {
          console.log(res);
          that.$emit("closedialog");
        });
      } else {
        // Create cookbook
        let newCookbook = (
          await Axios.put(
            `${urlSchema.profileUrl}newCookBook/${this.$store.getters["example/getUser"]}`,
            { name: this.newCookbookName }
          )
        ).data;

        Axios({
          method: "put",
          url: `${urlSchema.recipeUrl}newRecipe/${this.$store.getters["example/getUser"]}/${newCookbook.id}`,
          data: bodyFormData,
          headers: { "Content-Type": "multipart/form-data" },
        })
          .then((res) => {
            console.log(res);
            // Close dialog
            that.$emit("closedialog");
          })
          .catch((err) => {
            console.error(err);
          });
      }
    },

    async addRecipe() {
      console.log('hi');
      if (this.cookbook.value) {
        var that = this; 
        console.log('here');
        await Axios.post(`${urlSchema.profileUrl}saveRecipe/${this.$store.getters["example/getUser"]}/${this.cookbook.value.id}`, this.recipe)
        .then((res) => {
          // Close dialog
          that.$emit("closedialog");
        })
        .catch((err) => {
          console.error(err);
        });
      } else {
        // Create cookbook
        await Axios.put(
          `${urlSchema.profileUrl}newCookBook/${this.$store.getters["example/getUser"]}`,
          { name: this.newCookbookName }
        )
        .then((res) => {
          console.log('new cookbook', res)
          this.newCookbook = res.data;
        })

        var that = this; 
        await Axios.post(`${urlSchema.profileUrl}saveRecipe/${this.$store.getters["example/getUser"]}/${this.newCookbook.id}`, this.recipe)
        .then((res) => {
          // Close dialog
          that.$emit("closedialog");
        })
        .catch((err) => {
          console.error(err);
        });
      }
    },
  },
  watch: {
    value(newValue) {
      if (newValue) {
        this.getCookbooks();
      }
    },
  },
};
</script>
