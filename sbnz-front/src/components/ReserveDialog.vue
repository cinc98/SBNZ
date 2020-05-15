<template>
    <v-dialog v-model="show" persistent max-width="500">
      <v-card>
        <v-card-title>
          <span class="headline">Reserve car</span>
        </v-card-title>
        <div class="resevationFormInputs">
            <span class="inputSpans">
                From date:
            </span>
            <div>
                <DatePicker @change="resetView" :disabled-date="diasbledDates" v-model="fromDate" valueType="format"/>
            </div>
        </div >
                <div class="resevationFormInputs">
            <span class="inputSpans">
                Days:
            </span>
              <div class="showPrice">
               <v-text-field class="dayInput" @click="resetView" type="number" v-model="days" min="1"></v-text-field>
               <v-btn class="btnShowPrice" @click="showPrice">Show price</v-btn>
              </div>
        </div>
        <div v-if="showFinalPrice">

        
                <div class="resevationFormInputs">
            <span class="inputSpans">
                Price:
            </span>
            <span>
               <v-text-field v-model="price" readonly suffix="$"></v-text-field>
            </span>
        </div>
                <div class="resevationFormInputs">
            <span class="inputSpans">
               Discount:
            </span>
            <span>
               <v-text-field v-model="discout" suffix="%" readonly></v-text-field>
            </span>
        </div>
                <div class="resevationFormInputs">
            <span class="inputSpans">
                Final price:
            </span>
            <span>
               <v-text-field v-model="finalprice" suffix="$" readonly></v-text-field>
            </span>
        </div>
        </div>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialogToggleClose">Close</v-btn>
          <v-btn color="blue darken-1" text :disabled="!showFinalPrice" @click="saveReservation">Confirm</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>

<script>
 import DatePicker from 'vue2-datepicker';
import 'vue2-datepicker/index.css';
import axios from 'axios';
import moment from 'moment';

export default {
    name: 'ReserveDialog',
    components:{
        DatePicker
    },
    data(){
        return{
            fromDate : null,
            days:1,
            showFinalPrice: false,
            price:0,
            discout:0,
            finalprice:0,
            untilDate:'',
            date:''
        

        }
    },
    props: ['dialogToggle', 'show', 'carId'],
    methods:{
        showPrice(){
            if(this.fromDate !== null){
                 axios.post(`http://localhost:8080/reservation/reserve?carId=${this.$props.carId}&fromDate=${moment(this.fromDate).format('DD/MM/yyyy')}&numberOfDays=${this.days}&username=${sessionStorage.getItem('username')}`)
                    .then((response) => {
                        this.showFinalPrice = !this.showFinalPrice
                        this.price=response.data.price;
                        this.discout=response.data.discount;
                        this.finalprice=this.price*((100-this.discout)/100);
                        this.untilDate=response.data.untilDate;
                        this.date=response.data.fromDate;
                    })
                    .catch((error) => {
                        alert(error.response.data);
                    });
            }else{
                alert('Morate uneti datum!');
            }

        },
        dialogToggleClose(){
            this.showFinalPrice=false;
            this.$props.dialogToggle();
            this.fromDate=null;
            this.days=1;
        },
        resetView(e){
            this.showFinalPrice = false;
        },
        diasbledDates(data){
            if(data<new Date())
                return true ;
                else
                return false;
        },
        saveReservation(){
             axios.post(`http://localhost:8080/reservation/save`,{
                 discount: this.discout,
                 price: this.price,
                 fromDate: this.date,
                 untilDate: this.untilDate,
                 user:{
                     username: sessionStorage.getItem('username')
                 },
                 car:{
                     id:this.$props.carId
                 }
             })
                    .then((response) => {
                        this.dialogToggleClose();
                        alert(response.data);
                        
                    })
                    .catch((error) => {
                        alert(error.response.data);
                    });

        }
 
     

    }
    

}
</script>

<style>
.resevationFormInputs{
    display: flex;
    flex-direction: row;
    margin-left: 24px;
}
.resevationFormInputs span:nth-child(1){
        flex-basis: 150px;
        text-align: left;
        
        justify-content: center;

    }
.showPrice{
    display: flex;
}
.inputSpans{
    flex-direction: column;
    display: flex;
}
.dayInput{
    max-width: 70px !important;
}
.btnShowPrice{
    margin-top: 7px;
    margin-left: 10px;
}
</style>