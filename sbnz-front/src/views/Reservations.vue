<template>
    <div>
        <app-bar/>
        <div class="home">
            <div class="nav">
                <navigation/>
            </div>

            <div>

                <v-simple-table
              
                >
                <template >
                    <thead>
                    <tr>
                        <th class="text-left">Model</th>
                        <th class="text-left">Price</th>
                        <th class="text-left">Discount</th>
                        <th class="text-left">Final price</th>
                        <th class="text-left">From</th>
                        <th class="text-left">To</th>
                        <th class="text-left">Action</th>



                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="r in rs" :key="r.id">
                        <td>{{r.carObject.name}} {{r.carObject.model}}</td>
                        <td>{{r.price}} $</td>
                        <td>{{r.discount}} %</td>
                        <td>{{r.price*((100-r.discount)/100)}} $</td>
                        <td>{{r.fromDate.split('T')[0]}}</td>
                        <td>{{r.untilDate.split('T')[0]}}</td>
                        <td><v-btn @click="cancelReservation(r.id)">Cancel</v-btn></td>
                    </tr>
                    </tbody>
                </template>
                </v-simple-table>
            </div> 
            <cancel-dialog v-bind:dialogToggle="this.toggleDialogClick" v-bind:idRes="this.idRes" v-bind:penalty="this.penalty" v-bind:price="this.price"  v-bind:show="this.dialogToggle"/>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import Navigation from '../components/Navigation.vue';
import AppBar from '../components/AppBar.vue';
import CancelDialog from '../components/CancelDialog.vue';


export default {
    name: "Home",
    data() {
        return {
            dialogToggle:false,
            penalty:'',
            price:'',
            idRes:''
         
        };
    },
    computed:{
        rs(){
            return this.$store.state.activeReservations;
        }
    },
    components:{
        Navigation,
        AppBar,
        CancelDialog
    },
    methods:{
        cancelReservation(id){
              axios.get(`http://localhost:8080/reservation/cancel?reservationId=${id}`)
                .then((response) => {
                    this.idRes=id;
                    this.toggleDialogClick();
                    this.penalty=response.data.penaltyPercentage;
                    this.price=response.data.price;
                })
                .catch((error) => {
                    console.log(error);
                });
          
        },
        toggleDialogClick() {
            this.dialogToggle = !this.dialogToggle;
        },
        addActiveReservations(a) {
            this.$store.commit('fetchActiveReservations', a);
        },
    
     
    },
    created(){
           axios.get(`http://localhost:8080/reservation/all-active?username=${sessionStorage.getItem('username')}`)
                .then((response) => {
                    this.addActiveReservations(response.data);
                })
                .catch((error) => {
                    console.log(error);
                });
        
      }  
    
}
</script>

<style>
.home{
    height: 100%;
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