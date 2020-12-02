<template>
    <q-dialog :value="value" persistent @hide="$emit('input', false)">
        <q-card style="min-width: 350px">
            <q-card-section>
            <div class="text-h6">Save To CookBook</div>
            <div class="text-subtitle1">Select which cookbook you would like to add this recipe to</div>
            </q-card-section>

            <q-card-section class="q-pt-none">
            <q-select color="accent" dense v-model="cookbook" label="Cookbook" :options="cookbookList" >
            </q-select>

            <q-input color="accent" dense v-model="newCookbookName" label="New Cookbook Name" style="margin-top: 1rem;"
            placeholder="Enter the name of the new cookbook" :rules="[v => !!v || 'Required']" v-if="!cookbook.value"/>
            </q-card-section>

            <q-card-actions align="right" class="text-primary">
            <q-btn color="accent" flat label="Cancel" v-close-popup />
            <q-btn color="accent" flat label="Save" @click="saveToCookbook"/>
            </q-card-actions>
        </q-card>
    </q-dialog>
</template>

<script>
import Axios from 'axios';
const urlSchema = require("../SizzleUrls").default;
export default {
    name: 'add-cookbook',
    props: {
        value: {
            type: Boolean,
            required: true
        },
        recipe: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            userCookbooks: [],
            cookbook: { label: 'New cookbook', value: null },
            newCookbookName: ''
        };
    },
    computed: {
        cookbookList() {
            return [{ label: 'New cookbook', value: null }, ...this.userCookbooks.map(c => ({ label: c.name, value: c }))];
        } 
    },
    mounted() {
        this.getCookbooks();
    },
    methods: {
        getCookbooks() {
            Axios.get(`${urlSchema.profileUrl}getCookBooksFor/${this.$store.getters["example/getUser"]}`)
            .then(res => this.userCookbooks = res.data.cookBooks);
        },
        async saveToCookbook() {
            if (this.cookbook.value) {
                Axios.post(`${urlSchema.profileUrl}saveRecipe/${this.$store.getters["example/getUser"]}/${this.cookbook.value.id}/${this.recipe.id}`)
                .then(res => this.$emit('input', false))
                .catch(err => console.error(err));
            } else {
                try {
                    
                    // Create cookbook
                    let newCookbook = (await Axios.put(`${urlSchema.profileUrl}newCookBook/${this.$store.getters["example/getUser"]}`, { name: this.newCookbookName })).data;

                    // Add recipe to cookbook
                    await Axios.post(`${urlSchema.profileUrl}saveRecipe/${this.$store.getters["example/getUser"]}/${newCookbook.id}/${this.recipe.id}`);

                    // Close dialog
                    this.$emit('input', false);
                
                } catch (err) {
                    console.error(err);
                }
            }
        }
    },
    watch: {
        value(newValue) {
            if (newValue) {
                this.getCookbooks();
            }
        }
    }
}
</script>