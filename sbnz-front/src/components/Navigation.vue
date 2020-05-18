<template>
    <v-navigation-drawer
      permanent
      left
    >
      <template v-slot:prepend>
        <v-list-item two-line>
          <v-list-item-avatar>
            <img src="https://randomuser.me/api/portraits/men/81.jpg">
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
    </v-navigation-drawer>
</template>

<script>
export default {
    name: 'Navigation',
    computed:{
           user() {
            return sessionStorage.getItem('username');
        },
    },
    props:['dialogToggleSearchasd'],
    methods:{
      logout(){
        sessionStorage.removeItem('username');
        this.$store.commit('removeActiveReservations');
        this.$router.push('/');


      }
    }
}
</script>

<style>
</style>