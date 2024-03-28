<template>
  <div>
    <h1>13</h1>
    <input v-model="userInput" />
    <button @click="clickBtn()">click me</button>
    <table>
      <tr>
        <th v-for="header in headers" :key="header.id">{{ header.text }}</th>
        <th>操作</th>
      </tr>
      <tr v-for="data in datas" :key="data.id">
        <td>{{ data.name }}</td>
        <td>{{ data.id }}</td>
        <td>{{ data.scores }}</td>
        <td><a href="" @click="clickUrl(data)" class="large">修改</a><slot></slot></td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  emits: ['some-event', 'click-url-event'],
  props: {
    headers: Array,
    datas: Array
  },
  data() {
    return {
      userInput: ''
    }
  },
  methods: {
    clickBtn() {
      console.log(this.userInput)
      this.$emit('some-event', this.userInput)
    },
    clickUrl(data) {
      this.$emit('click-url-event', data)
    }
  }
}
</script>
<style scoped>
table,
th,
td {
  border: 1px solid black;
  border-collapse: collapse;
  width: 20%;
  text-align: center;
}
.large {
  font-size: large;
}
</style>
