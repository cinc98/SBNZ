<template>
    <v-dialog
      v-model="show"
     persistent max-width="600"
    
    >
      <v-card class="dialogRecommendationList">
        <v-card-title class="headline">Recommendation</v-card-title>
        <div class="autocompleteDivList">
            <v-select
                class="autocompleteRecommendationList"
                v-model="selectedUser"
                dense
                filled
                :items="users"
                @input="clickUser"
                label="Choose user"
            ></v-select>
        </div>
        <h3 v-if="recommendedUsers.length > 0">{{selectedUser}} recommended:</h3>
        <div v-for="u in recommendedUsers" :key="u">
            <h4>{{u}}</h4>
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
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>

<script>
import axios from 'axios';

export default {
    name: 'RecommendationListDialog',
    data(){
        return{
            users:[],
            selectedUser:'',
            recommendedUsers:[]


        }
    },
    props: ['show', 'dialogToggle'],
    methods:{ 
        closeDialog(){
            this.$props.dialogToggle();
            this.selectedUser='';
            this.recommendedUsers=[];
        },
        clickUser(){
            axios.get(`http://localhost:8080/user/check?first=${this.selectedUser}`)
                .then((response) => {
                    this.recommendedUsers=[];
                    response.data.forEach(user => {
                        if(user.recommended == true)
                            this.recommendedUsers.push(user.username);
                    });
                 
                  
                })
                .catch((error) => {
                    console.log(error);
            });
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
.autocompleteDivList{
    display: flex;
    padding-left: 24px;
    padding-right: 24px;
}
.dialogRecommendationList{
    height: auto !important;
}
.autocompleteRecommendationList{
    max-width: unset !important;
}
</style>