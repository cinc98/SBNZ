<template>
    <div>
        <app-bar/>
        <div class="home">
            <div class="nav">
                <navigation v-bind:dialogToggleSearchasd="this.toggleDialogSearchClick"/>
            </div>
            <div>
                <v-simple-table>
                <template >
                    <thead>
                    <tr>
                        <th class="text-left">ID</th>
                        <th class="text-left">Username</th>
                        <th class="text-left">Registration date</th>
                        <th class="text-left">Medal</th>

                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="u in users" :key="u.id">
                        <td>{{u.id}}</td>
                        <td>{{u.username}}</td>
                        <td>{{new Date(u.registrationDate).toLocaleString().split(",")[0]}}</td>
                        <td>{{u.medal}}</td>
                    </tr>
                    </tbody>
                </template>
                </v-simple-table>
                <v-btn @click="awardMedal">Award medal</v-btn>
            </div> 
            <search-dialog v-bind:dialogToggleS="this.toggleDialogSearchClick" v-bind:show="this.dialogToggleSearch"/>
            
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import Navigation from '../components/Navigation.vue';
import AppBar from '../components/AppBar.vue';
import SearchDialog from '../components/SearchDialog.vue';


export default {
    name: "Users",
    data() {
        return {
            dialogToggleSearch : false,
        };
    },
    computed:{
        users(){
            return this.$store.state.users;
        }
    
    },
    components:{
        Navigation,
        AppBar,
        SearchDialog
    },
    methods:{
        toggleDialogSearchClick() {
            this.dialogToggleSearch = !this.dialogToggleSearch;
        },
        addUsers(users) {
            this.$store.commit('fetchUsers', users);
        },
        awardMedal(){
            axios.get(`http://localhost:8080/user/category`)
                .then((response) => {
                    this.addUsers(response.data);
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    
     
    },
    created(){
           axios.get(`http://localhost:8080/user/all`)
                .then((response) => {
                    this.addUsers(response.data);
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