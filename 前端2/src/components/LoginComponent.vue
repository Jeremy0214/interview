<template>
  <div class="custom-bg">
    <div class="handling">
      <img
        alt="Vue logo"
        class="logo"
        src="@/assets/login.png"
        width="150"
        height="150"
      />
    </div>
    <a-form :label-col="{ span: 11 }" :wrapper-col="{ span: 6 }" class="border" :model="users">
      <br />
      <a-form-item
        label="Account"
        name="account"
        :rules="[{ required: true, message: 'Please input your username!' }]"
      >
        <a-input v-model:value="users.account" />
      </a-form-item>

      <a-form-item
        label="Password"
        name="password"
        :rules="[{ required: true, message: 'Please input your password!' }]"
      >
        <a-input-password v-model:value="users.password" />
      </a-form-item>

      <a-form-item :wrapper-col="{ offset: 11, span: 3 }">
        <a-button type="primary" @click="loginCheck">Submit</a-button>
      </a-form-item>
      <p v-if="showWarning" style="text-align: center">Please Enter Account and password</p>
    </a-form>
  </div>
</template>

<script>
import { useAuthStore } from '../stores/auth' //引用store
export default {
  data() {
    return {
      users: {
        account: '',
        userName: '',
        password: ''
      },
      store: useAuthStore(),
      showWarning: false
    }
  },
  methods: {
    loginCheck() {
      if (this.users.account == '' || this.users.password == '') {
        this.showWarning = true
        return
      }
      this.users.userName = this.users.account
      this.showWarning = false
      this.store
        .login(this.users)
        .then((response) => {
          console.log(response)
          this.$router.push('/')
        })
        .catch((error) => {
          alert('帳號或密碼錯誤')
        })
    }
  }
}
</script>
<style scoped>
.handling {
  text-align: center;
  margin-top: 100px;
}
.border {
  border: 2px solid;
  border-radius: 8px;
  width: 50%;
  margin-left: 25%;
  padding: 5px;
  background-color: antiquewhite;
}
.custom-bg {
  position:fixed;
  top:0;
  bottom:0;
  right:0;
  left:0;
  background-image: url('@/assets/cat.jpg');
  background-size: 100% 100%;
}
</style>
