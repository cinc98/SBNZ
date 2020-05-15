<template>
        <v-card
            class="mx-auto my-12 carCards"
            max-width="350"
        >
            <v-img
            height="250"
            :src="img"
            ></v-img>

            <v-card-title >{{name}} {{model}}</v-card-title>
            <h5>{{price}}$/day</h5>
            <v-card-text>   
            <div>Date: {{date.split('T')[0]}}</div>
                <div>KM: {{km}}</div>

            </v-card-text>

            <v-divider class="mx-4"></v-divider>
            <v-card-actions>
            <v-btn
                color="deep-purple lighten-2"
                text
                @click="toggleDialogClick"
            >
                Reserve
            </v-btn>
            </v-card-actions>
                <reserve-dialog v-bind:dialogToggle="this.toggleDialogClick" v-bind:carId="carId" v-bind:show="this.dialogToggle"/> 

        </v-card>

</template>

<script>
import ReserveDialog from './ReserveDialog.vue';

export default {
    name: 'CarCard',
    components:{
        ReserveDialog
    },
    props:['name','km','price','date', 'model','img', 'carId'],
    data() {
        return {
            cars: null,
            dialogToggle:false,
        };
    },
    methods: {
        toggleDialogClick() {
            this.dialogToggle = !this.dialogToggle;
        },
    },
}
</script>

<style>
.carCards{
    height: auto !important;
}

</style>