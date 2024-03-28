<template>
  <button @click="clickBtn">==</button>
  <button @click="addBtn">compile</button>
  <button @click="getResponse">get</button>
  <button @click="postResponse">post</button>
  <a-button type="primary" >Primary</a-button>


</template>
<script>
import { mapStores, mapState, mapActions } from 'pinia'
import { useCartStore } from '../stores/cart' //引用store
import axios from 'axios'
export default {
  name: 'CartView',
  components: {},
  computed: {
    ...mapStores(useCartStore), // map store, 官網上說到當map後就可以用this.id+'Store'的方式取得store的變數
    // gives read access to this.items and this.totalCost
    ...mapState(useCartStore, ['value', 'doubleCount']) //map state
  },
  methods: {
    ...mapActions(useCartStore, ['increment']), // map action,
    addBtn() {
      this.cartStore.increment()
    },
    clickBtn() {
      console.log(this.cartStore.value) // 這邊就是上方指的 this.id+'Store',id是'cart',因此這邊就是this.cartStore,算是一個我自己覺得比較奇怪的用法
      console.log(this.cartStore.doubleCount)
    },
    mounted() {
      // react to store changes
      this.cartStore.$subscribe((mutation, state) => {
        console.log(mutation, state)
      })
    },
    getResponse() {
      axios
        .get('https://randomuser.me/api/')
        .then((response) => console.log(response))
        .catch((error) => console.log(error))
    },
    postResponse() {
      axios
        .post('https://hexschool-tutorial.herokuapp.com/api/signup', {
          email: 'javascriptBasics@gmail.com',
          password: '1234'
        })
        .then((response) => console.log(response))
        .catch((error) => console.log(error))
    },
    getRandomUser() {
      axios
        .get('https://randomuser.me/api/?gender=female&nat=us')
        .then((response) => console.log(response))
        .catch((error) => console.log(error))
    }
  }
}
</script>
