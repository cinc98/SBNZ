<template>
  <v-dialog
      v-model="show"
      persistent
      width="500"
    >
      <v-card class="dialogNotification">
        <v-card-title
          class="headline grey lighten-2"
          primary-title
        >
          Notifications
        </v-card-title>

        <v-card-text v-if="smanjiti.length > 0">
            Potrebno je smanjiti cenu:
        </v-card-text>
         
        <v-card-text v-for="c in smanjiti" :key="c.id">
           {{c.id}} {{c.name}} {{c.model}}
        </v-card-text>

        <v-card-text v-if="povecati.length > 0">
            Potrebno je povecati cenu:
        </v-card-text>
         
        <v-card-text v-for="c in povecati" :key="c.date">
           {{c.id}} {{c.name}} {{c.model}}
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="primary"
            text
            @click="dialogToggle"
          >
            Close
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>

<script>
export default {
    name: "NotificationDialog",
    props:['show','dialogToggle','povecati', 'smanjiti']

}
</script>

<style>
.dialogNotification{
    height: auto !important;
}
</style>