import { defineStore } from 'pinia'
import api from '@/service/auth.js'
export const useAuthStore = defineStore({
  id: 'auth',
  state: () => ({
    token: null,
    users: {
      account: '',
      name: '',
      users: ''
    }
  }),
  actions: {
    setToken(token) {
      this.token = token
    },
    setUser({ account, userName }) {
      this.users.account = account
      this.users.name = userName
      console.log(this.users.name)
    },
    async login(users) {
      this.setUser(users)

      const checkUser = {
        email: users.account,
        password: users.password
      }

      console.log('checkUser', checkUser)
      const response = await api.login(checkUser)

      this.setToken('77777')
      localStorage.setItem('token', this.token)
      localStorage.setItem('name', this.users.name)

      return response
    },
    async logout() {
      //   this.users = null
      //   this.token = null
      this.$reset()
      localStorage.clear()
    },
    getUserName() {
      return localStorage.getItem('name', this.users.name)
    }
  }
})
