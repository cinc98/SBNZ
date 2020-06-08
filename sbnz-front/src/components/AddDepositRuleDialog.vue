<template>
  <v-dialog v-model="show" persistent max-width="400">
    <v-card class="cardAddRule">
      <v-card-title class="headline">Add rule for deposit</v-card-title>
      <div class="addCardFormInputs">
        <span class="formInputSpans">Price from:</span>
        <div>
          <v-text-field class="priceInput" type="number" v-model="priceFrom" min="0" suffix="$"></v-text-field>
        </div>
      </div>
      <div class="addCardFormInputs">
        <span class="formInputSpans">Price to:</span>
        <div>
          <v-text-field class="priceInput" type="number" v-model="priceTo" min="0" suffix="$"></v-text-field>
        </div>
      </div>
      <div class="addCardFormInputs">
        <span class="formInputSpans">Deposit:</span>
        <div>
          <div class="depositRange">
            <v-text-field
              class="depositInput"
              type="range"
              v-model="deposite"
              min="0"
              max="100"
            ></v-text-field>
            <div class="depositeValue">
            {{this.deposite}} %

            </div>
          </div>
        </div>
      </div>

      <v-card-actions>
        <v-spacer></v-spacer>

        <v-btn color="red darken-1" text @click="closeDialog">Cancel</v-btn>

        <v-btn color="green darken-1" text @click="agree">Submit</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import axios from "axios";

export default {
  name: "AddDepositRuleDialog",
  data() {
    return {
      priceFrom: "",
      priceTo: "",
      deposite: 0
    };
  },
  props: ["show", "dialogToggle"],
  methods: {
    closeDialog() {
      this.$props.dialogToggle();
      this.priceTo = "";
      this.priceFrom = "";
      this.deposite = 0;
    },
    agree() {
      if (this.priceFrom !== "" && this.priceTo !== "") {
        axios
          .post(
            `http://localhost:8080/user/add-rule?minPrice=${this.priceFrom}&maxPrice=${this.priceTo}&depositValue=${this.deposite}`
          )
          .then(response => {
            this.closeDialog();
            alert(response.data);
          })
          .catch(error => {
            alert(error.response.data);
          });
      } else {
        alert("Popunite sva polja!");
      }
    }
  }
};
</script>

<style>
.cardAddRule {
  height: 350px !important;
}
.addCardFormInputs {
  display: flex;
  flex-direction: row;
  margin-left: 24px;
}
.addCardFormInputs span:nth-child(1) {
  flex-basis: 150px;
  text-align: left;
  justify-content: center;
}
.formInputSpans {
  flex-direction: column;
  display: flex;
}
.depositInput {
  width: 150px !important;
}
.depositRange {
  display: flex;
}
.depositeValue{
  margin-top: 25px;
  margin-left: 10px;
}

.priceInput {
  width: 185px !important;
}

</style>