 
import { defineStore } from 'pinia'
 
export const useCartStore = defineStore('cart', {
  //定義store, 這邊的'cart'會成為這個store的id,在取用store時會使用
  state: () => ({
    // 1. State: 定義狀態初始值
    value: 0
  }),
  getters: {
    // 2. getters: 對狀態加工的 getters，如同 computed
    doubleCount() {
      return this.value * 2
    }
  },
  actions: {
    // 3. action: 定義使用到的函式，可以為同步和非同步，如同 method
    increment() {
      this.value++
    }
  }
})