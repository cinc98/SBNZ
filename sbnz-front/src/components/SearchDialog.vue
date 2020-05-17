<template>
    <v-dialog  v-model="show" persistent max-width="600px">
        <v-card class="searchDialog">
        <v-card-title>
          <span class="headline">Search car</span>
        </v-card-title>
        <div class="inputsSearch" >
        <div class="searchFormInputs">
              
            <div>
                <v-select
                    label="Country"
                    v-model="country"
                    :items = "['Germany', 'France', 'USA', 'Italy', 'Sweden', 'United Kingdom']"
                ></v-select>
                
            </div>
        </div >
            <div class="searchFormInputs">
               
            <div>
                <v-select
                    label="Vehicle type"
                    v-model="vehicle_type"
                    :items = "['Coupe', 'SUV', 'Saloon', 'Cabriolet', 'Small Car']"
                ></v-select>
                
            </div>
        </div >
            <div class="searchFormInputs">
              
            <div>
                <v-select
                    v-model="fuel_type"
                    label="Fuel Type"
                    :items = "['Petrol', 'Diesel', 'LPG']"
                ></v-select>
                
            </div>
        </div >
            <div class="searchFormInputs">
             
            <div>
                <v-select
                    v-model="transmission"
                    label="Transmission"
                    :items = "['Manual gearbox', 'Automatic transmission']"
                ></v-select>
                
            </div>
        </div >
            <div class="searchFormInputs">
             
            <div>
                <v-select
                    v-model="equipment"
                    label="Equipment"
                    :items = "['ABS', 'Navigation system', 'ESP', '360 camera', 'Sport seats', 'LED headlights']"
                ></v-select>
                
            </div>
        </div >
            <div class="searchFormInputs">
               
            <div>
                <v-select
                    v-model="interior_material"
                    label="Interior material"
                    :items = "['Full leather', 'Part leather', 'Cloth']"
                ></v-select>
                
            </div>
        </div >
        </div>
        <div class="checkBoxSearch">
            <v-checkbox v-model="specific[0]"
                        v-bind:false-value="'x'"
                        v-bind:true-value="'Night vision assist'"
                        :disabled="this.specific.filter(s => s != 'x').length > 2 && specific[0] === 'x'"
                        class="mx-2" label="Night vision assist"></v-checkbox>
            <v-checkbox v-model="specific[1]"
                        :disabled="this.specific.filter(s => s != 'x').length > 2 && specific[1] === 'x'"
                        v-bind:false-value="'x'"
                        v-bind:true-value="'Sports suspension'" class="mx-2" label="Sports suspension"></v-checkbox>
            <v-checkbox v-model="specific[2]"
                        :disabled="this.specific.filter(s => s != 'x').length > 2 && specific[2] === 'x'"
                        v-bind:false-value="'x'"
                        v-bind:true-value="'Voice control'" class="mx-2" label="Voice control"></v-checkbox>
            <v-checkbox  v-model="specific[3]"
                        :disabled="this.specific.filter(s => s != 'x').length > 2 && specific[3] === 'x'"
                        v-bind:false-value="'x'"
                        v-bind:true-value="'Heated steering wheel'" class="mx-2" label="Heated steering wheel"></v-checkbox>
            <v-checkbox  v-model="specific[4]"
                        :disabled="this.specific.filter(s => s != 'x').length > 2 && specific[4] === 'x'"
                        v-bind:false-value="'x'"
                        v-bind:true-value="'Hands-free'" class="mx-2" label="Hands-free"></v-checkbox>
            <v-checkbox  v-model="specific[5]"
                        :disabled="this.specific.filter(s => s != 'x').length > 2 && specific[5] === 'x'"
                        v-bind:false-value="'x'"
                        v-bind:true-value="'ISOFIX'" class="mx-2" label="ISOFIX"></v-checkbox>
            <v-checkbox  v-model="specific[6]"
                        :disabled="this.specific.filter(s => s != 'x').length > 2 && specific[6] === 'x'"
                        v-bind:false-value="'x'"
                        v-bind:true-value="'Digital cockpit'" class="mx-2" label="Digital cockpit"></v-checkbox>
            <v-checkbox  v-model="specific[7]"
                        :disabled="this.specific.filter(s => s != 'x').length > 2 && specific[7] === 'x'"
                        v-bind:false-value="'x'"
                        v-bind:true-value="'Start-stop system'" class="mx-2" label="Start-stop system"></v-checkbox>
            <v-checkbox  v-model="specific[8]"
                        :disabled="this.specific.filter(s => s != 'x').length > 2 && specific[8] === 'x'"
                        v-bind:false-value="'x'"
                        v-bind:true-value="'Bi-xenon headlights'" class="mx-2" label="Bi-xenon headlights"></v-checkbox>

        </div>

          <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="closeDialog">Close</v-btn>
          <v-btn color="blue darken-1" text @click="searchCar" >Search</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>

<script>
import axios from 'axios';

export default {
    name: "SearchDialog",
    data(){
        return{
            specific:['x','x','x','x','x','x','x','x','x'],
            country : '',
            vehicle_type: '',
            fuel_type : '',
            transmission : '',
            equipment : '',
            interior_material : '',
           
        }
    },
    props: ['show', 'dialogToggleS'],
    methods:{
        searchCar(){
            this.specific=this.specific.filter(s => s !='x');
             axios.get(`http://localhost:8080/searchCar/s?zemlja=${this.country}&karoserija=${this.vehicle_type}&gorivo=${this.fuel_type}&menjac=${this.transmission}&oprema=${this.equipment}&sedista=${this.interior_material}&s1=${this.specific[0]}&s2=${this.specific[1]}&s3=${this.specific[2]}`)
                    .then((response) => {
                        if(response.data.model === null){
                            alert("Nije pronadjeno vozilo za vas!");
                        }else{
                            this.$store.commit('addModel',response.data.model);
                            this.closeDialog();
                            this.$router.push('/search-result');

                        }
                     
                    })
                    .catch((error) => {
                        alert(error.response.data);
                    });

         
        },
        closeDialog(){
            this.dialogToggleS();
            this.specific=['x','x','x','x','x','x','x','x','x'];
            this.country = '';
            this.vehicle_type= '';
            this.fuel_type = '';
            this.transmission = '';
            this.equipment = '';
            this.interior_material = '';
        }
     
    }

}
</script>

<style>
.searchFormInputs{
    display: inline-flex;
    flex-direction: row;
    margin-left: 24px;
}

.searchSpans{
    flex-direction: column;
    display: flex;
}
.searchDialog{
    height: auto !important;
}
.checkBoxSearch{
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    margin-left: 24px;
}
.v-select{
    max-width: 252px !important;
    width: 252px !important;
}
.inputsSearch{
    display: flex;
    flex-wrap: wrap;
}
</style>