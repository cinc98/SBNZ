<template>
    <v-dialog
      v-model="show"
     persistent max-width="600"
    
    >
      <v-card class="dialogRecommendation">
        <v-card-title class="headline">Recommendation</v-card-title>
        <div class="autocompleteDiv">
            <v-select
                class="autocompleteRecommendation"
                v-model="selectedUser"
                dense
                filled
                :items="users"
                label="My account recommended from"
            ></v-select>
        </div>


        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
            color="red darken-1"
            text
            @click="closeDialog"
          >
            Close
          </v-btn>

          <v-btn
            color="green darken-1"
            text
            @click="submitRecommendation"

          >
            Submit
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>

<script>
import axios from 'axios';

export default {
    name: 'RecommendationDialog',
    data(){
        return{
            users:[],
            selectedUser:''

        }
    },
    props: ['show', 'dialogToggle'],
    methods:{
        submitRecommendation(){
            if(this.selectedUser === ''){
                alert("Niste selektovali usera!")
            }else{
                axios.post(`http://localhost:8080/user/new-recommendation?first=${this.selectedUser}&second=${sessionStorage.getItem('username')}`)
                    .then((response) => {
                        alert(response.data);
                        this.closeDialog();

                    })
                    .catch((error) => {
                        alert(error.response.data);
                    });

            }
        },
        closeDialog(){
            this.$props.dialogToggle();
            this.selectedUser='';
        }
    },
    created(){
           axios.get('http://localhost:8080/user/all')
                .then((response) => {
                    response.data.forEach(user => {
                        if(user.username != sessionStorage.getItem('username'))
                            this.users.push(user.username);
                    });
                  
                })
                .catch((error) => {
                    console.log(error);
                });
        
      
    },

}
</script>

<style>
.autocompleteDiv{
    display: flex;
    padding-left: 24px;
    padding-right: 24px;
}
.dialogRecommendation{
    height: 200px !important;
}
.autocompleteRecommendation{
    max-width: unset !important;
}
</style>