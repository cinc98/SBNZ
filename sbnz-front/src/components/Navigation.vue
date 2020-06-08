<template>
    <v-navigation-drawer
      permanent
      left
    >
      <template v-slot:prepend>
        <v-list-item two-line>
          <v-badge
            v-if="user === 'admin'"
            :value="badgeShow"
            bordered
            bottom
            color="red"
            icon="mdi-bell"
            offset-x="30"
            offset-y="30"
        
          >
            <v-list-item-avatar size="50">
              <img @click="imageClick" src="https://randomuser.me/api/portraits/men/81.jpg">
            </v-list-item-avatar>
          </v-badge>

           <v-list-item-avatar size="50" v-else>
            <img src="https://randomuser.me/api/portraits/men/86.jpg">
          </v-list-item-avatar>



          <v-list-item-content>
            <v-list-item-title>{{user}}</v-list-item-title>
            <v-list-item-subtitle>Logged In</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </template>

      <v-divider></v-divider>

        <v-list >
          <v-list-item link to="/home">
                <v-list-item-icon>
                <v-icon>home</v-icon>
                </v-list-item-icon>

                <v-list-item-content>
                <v-list-item-title>Home</v-list-item-title>
                </v-list-item-content>
          </v-list-item>
          <v-list-item link to="/reservations">
                <v-list-item-icon>
                <v-icon>list</v-icon>
                </v-list-item-icon>

                <v-list-item-content>
                <v-list-item-title>Reservations</v-list-item-title>
                </v-list-item-content>
          </v-list-item>
          <v-list-item @click="dialogToggleSearchasd" link>
                <v-list-item-icon>
                <v-icon>search</v-icon>
                </v-list-item-icon>

                <v-list-item-content>
                <v-list-item-title>Search</v-list-item-title>
                </v-list-item-content>
          </v-list-item>
          <v-list-item v-if="user !== 'admin'" @click="recommendationDialogToggle" link>
                <v-list-item-icon>
                <v-icon class="icon-style">recommendation</v-icon>
                </v-list-item-icon>

                <v-list-item-content>
                <v-list-item-title>Recommendation</v-list-item-title>
                </v-list-item-content>
          </v-list-item>
          <v-list-item v-if="user === 'admin'" @click="recommendationListDialogToggle" link>
                <v-list-item-icon>
                <v-icon class="icon-style">recommendation</v-icon>
                </v-list-item-icon>

                <v-list-item-content>
                <v-list-item-title>Recommendation list</v-list-item-title>
                </v-list-item-content>
          </v-list-item>
          <v-list-item v-if="user === 'admin'" @click="addRuleForDepositDialogToggle" link>
                <v-list-item-icon>
                <v-icon class="icon-style">queue</v-icon>
                </v-list-item-icon>

                <v-list-item-content>
                <v-list-item-title>Add rule for deposit</v-list-item-title>
                </v-list-item-content>
          </v-list-item>
          <v-list-item v-if="user === 'admin'" link to="/users">
                <v-list-item-icon>
                <v-icon>people</v-icon>
                </v-list-item-icon>

                <v-list-item-content>
                <v-list-item-title>Users</v-list-item-title>
                </v-list-item-content>
          </v-list-item>
        </v-list>
        <template v-slot:append>
            <div class="pa-2">
            <v-btn @click="logout" block color="blue white--text">Logout</v-btn>
            </div>
      </template>
      <notification-dialog v-bind:dialogToggle="this.dialogHide" v-bind:smanjiti="this.smanjiti" v-bind:povecati="this.povecati" v-bind:show="this.dialogToggle"/>
    </v-navigation-drawer>
</template>

<script>
import axios from 'axios';
import NotificationDialog from './NotificationDialog.vue';

export default {
    name: 'Navigation',
    data(){
      return{
        povecati:[],
        smanjiti:[],
        dialogToggle: false,
      }
    },
    components:{
      NotificationDialog,
    },
    computed:{
        user() {
            return sessionStorage.getItem('username');
        },
        badgeShow(){
          return this.$store.state.badgeShow;
        }
    },
    props:['dialogToggleSearchasd', 'recommendationDialogToggle','recommendationListDialogToggle', 'addRuleForDepositDialogToggle'],
    methods:{
      logout(){
        sessionStorage.removeItem('username');
        this.$store.commit('removeActiveReservations');
        this.$store.commit('showBadge');
        this.$router.push('/');


      },
      dialogHide(){
        this.dialogToggle=false;
      },
      imageClick(){
          this.$store.commit('hideBadge');
          axios.get(`http://localhost:8080/user/get-notifications`)
            .then((response) => {
              this.smanjiti=[];
              this.povecati=[];
              response.data.forEach(car => {
                      if(car.changePrice === 1)
                        this.povecati.push(car);
                      else if(car.changePrice === -1)
                        this.smanjiti.push(car);
                  });
              this.dialogToggle=true;
            })
            .catch((error) => {
                console.log(error);
            });
        
      }
    }
}
</script>

<style>
.icon-style{
  display: unset !important;
  width: 24px;
}
</style>