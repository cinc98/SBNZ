<template>
  <v-dialog
      v-model="show"
     persistent max-width="400"
    
    >
      <v-card class="dialog">
        <v-card-title class="headline">Cancel reservation</v-card-title>
        <h3>Rental price: {{price}} $</h3>
        <h5>You have to pay {{penalty}}% due to cancellation!</h5>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
            color="red darken-1"
            text
            @click="dialogToggle"
          >
            Disagree
          </v-btn>

          <v-btn
            color="green darken-1"
            text
            @click="agree"
          >
            Agree
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>

<script>
import axios from 'axios';


export default {
    name: 'CancelDialog',
    data(){
        return{

        };
    },
    props: ['dialogToggle', 'show', 'price', 'penalty', 'idRes' ],
    methods:{
        agree(){
              axios.post(`http://localhost:8080/reservation/cancel-agree?reservationId=${this.$props.idRes}&penalty=${this.$props.penalty}`)
                .then((response) => {
                    this.$store.commit('removeActiveReservation', this.$props.idRes);
                    alert(response.data);
                    this.dialogToggle();
                })
                .catch((error) => {
                    console.log(error);
                });


        }
    }

 

}
</script>

<style>
.dialog{
    height: 157px !important;
}

</style>