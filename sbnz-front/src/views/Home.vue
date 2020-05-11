<template>
    <button @click="test">Button</button>
</template>

<script>
import axios from 'axios';

export default {
    name: "Home",
    methods: {
      test(){
        axios.get('http://localhost:8080/item?id=1&name=pera&cost=233&salePrice=200')
                .then((response) => {
                    console.log(response.data);
                
                })
                .catch((error) => {
                    console.log(error);
                });
        
      }  
    }
}
</script>

<style>

</style>