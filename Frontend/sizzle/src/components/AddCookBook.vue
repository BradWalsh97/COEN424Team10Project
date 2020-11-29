<template>
    <q-dialog :value="value" persistent @hide="$emit('input', false)">
        <q-card style="min-width: 350px">
            <q-card-section>
            <div class="text-h6">Create new Cookbook</div>
            </q-card-section>

            <q-card-section class="q-pt-none">
            <q-input color="accent" dense v-model="name" label="Name" placeholder="Enter the new cookbook's name" autofocus @keyup.enter="addCookbook" :rules="[v => !!v || 'Required']" />
            </q-card-section>

            <q-card-actions align="right" class="text-primary">
            <q-btn color="accent" flat label="Cancel" v-close-popup />
            <q-btn color="accent" flat label="Add Cookbook" @click="addCookbook"/>
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
        }
    },
    data() {
        return {
            name: ''
        };
    },
    methods: {
        addCookbook() {
            Axios.put(`${urlSchema.profileUrl}newCookBook/${this.$store.getters["example/getUser"]}`, { name: this.name })
            .then(res => console.log(res))
            .catch(err => console.error(err));
        }
    }
}
</script>