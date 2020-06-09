<template>
  <div>
    <app-bar />
    <div class="home">
      <div class="nav">
        <navigation
          v-bind:addRuleForDepositDialogToggle="this.addRuleForDepositDialogToggleClick"
          v-bind:dialogToggleSearchasd="this.toggleDialogClick"
          v-bind:recommendationDialogToggle="this.toggleRecommendationDialogClick"
          v-bind:recommendationListDialogToggle="this.toggleRecommendationListDialogClick"
        />
      </div>
      <div class="cards">
        <car-card
          v-for="car in cars"
          :key="car.id"
          v-bind:name="car.name"
          v-bind:carId="car.id"
          v-bind:img="car.image"
          v-bind:km="car.km"
          v-bind:date="car.date"
          v-bind:price="car.price"
          v-bind:model="car.model"
        />
      </div>
      <div>
        <search-dialog
          v-bind:dialogToggleS="this.toggleDialogClick"
          v-bind:show="this.dialogToggle"
        />
      </div>
      <div>
        <recommendation-dialog
          v-bind:dialogToggle="this.toggleRecommendationDialogClick"
          v-bind:show="this.recommendationDialogToggle"
        />
      </div>
      <div>
        <recommendation-list-dialog
          v-bind:dialogToggle="this.toggleRecommendationListDialogClick"
          v-bind:show="this.recommendationListDialogToggle"
        />
      </div>
        <div>
        <add-deposit-rule-dialog
          v-bind:dialogToggle="this.addRuleForDepositDialogToggleClick"
          v-bind:show="this.addRuleDialogToggle"
        />
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Navigation from "../components/Navigation.vue";
import CarCard from "../components/CarCard.vue";
import AppBar from "../components/AppBar.vue";
import SearchDialog from "../components/SearchDialog.vue";
import RecommendationDialog from "../components/RecommendationDialog.vue";
import RecommendationListDialog from "../components/RecommendationListDialog.vue";
import AddDepositRuleDialog from '../components/AddDepositRuleDialog.vue';

export default {
  name: "Home",
  data() {
    return {
      cars: null,
      dialogToggle: false,
      recommendationDialogToggle: false,
      recommendationListDialogToggle: false,
      addRuleDialogToggle: false
    };
  },
  components: {
    Navigation,
    CarCard,
    AppBar,
    SearchDialog,
    RecommendationDialog,
    RecommendationListDialog,
    AddDepositRuleDialog
  },
  methods: {
    toggleDialogClick() {
      this.dialogToggle = !this.dialogToggle;
    },
    toggleRecommendationDialogClick() {
      this.recommendationDialogToggle = !this.recommendationDialogToggle;
    },
    toggleRecommendationListDialogClick() {
      this.recommendationListDialogToggle = !this
        .recommendationListDialogToggle;
    },
    addRuleForDepositDialogToggleClick() {
      this.addRuleDialogToggle = !this.addRuleDialogToggle;
    }
  },
  created() {
    axios
      .get("http://localhost:8080/car/all")
      .then(response => {
        this.cars = response.data;
      })
      .catch(error => {
        console.log(error);
      });
  }
};
</script>

<style>
.home {
  min-height: calc(100vh - 72px);
  display: flex;
}
.cards {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
</style>