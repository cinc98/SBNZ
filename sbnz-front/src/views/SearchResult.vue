<template>
    <div>
        <app-bar/>
        <div class="home">
            <div class="nav">
                <navigation v-bind:dialogToggleSearchasd="this.toggleDialogClick"/>
            </div>
            <div class="cards">
                    <car-card v-for="car in cars" :key="car.id" v-bind:name="car.name" v-bind:carId="car.id" v-bind:img="car.image" v-bind:km="car.km" v-bind:date="car.date" v-bind:price="car.price" v-bind:model="car.model"/>
                
            </div> 
            <div>
                <search-dialog v-bind:dialogToggleS="this.toggleDialogClick" v-bind:show="this.dialogToggle"/>
            </div>

        </div>
    </div>
</template>

<script>
import axios from 'axios';
import Navigation from '../components/Navigation.vue';
import CarCard from '../components/CarCard.vue'
import AppBar from '../components/AppBar.vue';
import SearchDialog from '../components/SearchDialog.vue'


export default {
    name: "SearchResult",
    data() {
        return {
            cars: null,
            dialogToggle:false,
        };
    },
    components:{
        Navigation,
        CarCard,
        AppBar,
        SearchDialog
    },
    methods:{
        toggleDialogClick() {
            this.dialogToggle = !this.dialogToggle;
        },
    },
    created(){
           axios.get(`http://localhost:8080/car/model?model=${this.$store.state.model}`)
                .then((response) => {
                    this.cars=response.data;
                })
                .catch((error) => {
                    console.log(error);
                });
        
    },
    updated(){
           axios.get(`http://localhost:8080/car/model?model=${this.$store.state.model}`)
                .then((response) => {
                    this.cars=response.data;
                })
                .catch((error) => {
                    console.log(error);
                });
        
      }  
    
}
</script>

<style>
.home{
    min-height: calc(100vh - 72px);
    display: flex;
}
.cards{

    display: flex;
    flex-direction: row;
    flex-wrap: wrap;

}
.nav{
   
}

</style>