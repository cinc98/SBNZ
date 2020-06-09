<template>
  <v-dialog v-model="show" persistent max-width="400">
    <v-card class="cardExtend">
      <v-card-title>
        <span class="headline">Extend reservation</span>
      </v-card-title>
      <div class="resevationFormInputs">
        <span class="inputSpans">Days:</span>
        <div class="showPrice">
          <v-text-field class="dayInput" @click="resetView" type="number" v-model="days" min="1"></v-text-field>
          <v-btn class="btnShowPrice" @click="showPrice">Show price</v-btn>
        </div>
      </div>
      <div v-if="showFinalPrice">
        <div class="resevationFormInputs">
          <span class="inputSpans">Price:</span>
          <span>
            <v-text-field v-model="price" readonly suffix="$"></v-text-field>
          </span>
        </div>
        <div class="resevationFormInputs">
          <span class="inputSpans">Discount:</span>
          <span>
            <v-text-field v-model="discout" suffix="%" readonly></v-text-field>
          </span>
        </div>
        <div class="resevationFormInputs">
          <span class="inputSpans">Final price:</span>
          <span>
            <v-text-field v-model="finalprice" suffix="$" readonly></v-text-field>
          </span>
        </div>
      </div>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" text @click="dialogToggleClose">Close</v-btn>
        <v-btn
          color="blue darken-1"
          text
          :disabled="!showFinalPrice"
          @click="extendReservation"
        >Confirm</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import axios from "axios";
import moment from "moment";

export default {
  name: "CancelDialog",
  data() {
    return {
      days: 1,
      showFinalPrice: false,
      price: 0,
      discout: 0,
      finalprice: 0,
      date: "",
      toDate: "",
      carId: ""
    };
  },
  props: ["dialogToggle", "show", "untilDate", "idRes"],
  methods: {
    showPrice() {
      axios
        .post(
          `http://localhost:8080/reservation/extend?numberOfDays=${
            this.days
          }&idRes=${this.$props.idRes}&fromDate=${moment(
            this.$props.untilDate
          ).format("DD/MM/yyyy")}`
        )
        .then(response => {
          this.showFinalPrice = !this.showFinalPrice;
          this.price = response.data.price;
          this.discout = response.data.discount;
          this.finalprice = this.price * ((100 - this.discout) / 100);
          this.toDate = response.data.untilDate;
          this.date = response.data.fromDate;
          this.carId = response.data.car;
        })
        .catch(error => {
          alert(error.response.data);
        });
    },
    extendReservation() {
      axios
        .post(`http://localhost:8080/reservation/save`, {
          discount: this.discout,
          price: this.price,
          fromDate: this.date,
          untilDate: this.toDate,
          user: {
            username: sessionStorage.getItem("username")
          },
          car: {
            id: this.carId
          }
        })
        .then(response => {
          this.dialogToggleClose();
          alert(response.data);
          window.location.reload();
        })
        .catch(error => {
          alert(error.response.data);
        });
    },
    resetView(e) {
      this.showFinalPrice = false;
    },
    dialogToggleClose() {
      this.showFinalPrice = false;
      this.$props.dialogToggle();
      this.days = 1;
    }
  }
};
</script>

<style>
.cardExtend {
  height: 400px !important;
}
</style>