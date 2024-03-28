<template>
  <div>
    <!-- 1 -->
    <div>
      <br />
      <p>{{ msg }}</p>
      <button @click="reverse">reverse</button>
      <button @click="append">!</button>
    </div>
    <!-- 2 -->
    <br />
    <p :class="{ red: isRed }" @click="toggleRed">
      This should be red... but click me to toggle it.
    </p>
    <!-- style bindings also support object and arrays -->
    <p :style="{ color }" @click="toggleColor">
      This should be green, and should toggle between green and blue on click.
    </p>
    <!-- 3 -->
    <br />
    <li v-if="show">{{ list }}</li>
    <p v-else></p>
    <div>
      <button @click="show = !show">Toggle List</button>
      <button @click="list.push(list.length + 1)">Push Number</button>
      <button @click="list.pop()">Pop Number</button>
      <button @click="list.reverse()" class="Large">Reverse List</button>
    </div>
    <!-- 4 -->
    <br />
    <div>
      <table>
        <tr>
          <td v-for="n in values" :key="n.id">{{ n.name }}</td>
        </tr>
        <tr>
          <td v-for="n in values" :key="n.id">{{ n.id }}</td>
        </tr>
        <tr>
          <td v-for="n in values" :key="n.id">{{ n.scores }}</td>
        </tr>
      </table>
    </div>
    <br />

    <br />
    <div>
      <button @click="sortByScores">按分数排序</button>
    </div>
    <br />
    <!-- 6 -->
    <br />
    <li v-for="n in numbers" :key="n.id">{{ n }}</li>
    <br />
    <button @click="getEvenNumbers">getEven</button>
  </div>
</template>

<script>
export default {
  name: 'hm1',
  data() {
    return {
      msg: 'Hello World!',
      isRed: true,
      isGreen: true,
      list: [1, 2, 3],
      show: true,
      color: 'green',
      values: [
        { name: 'Andy', id: 2, scores: 98 },
        { name: 'David', id: 1, scores: 97 },
        { name: 'John', id: 3, scores: 100 },
        { name: 'Terry', id: 4, scores: 80 },
        { name: 'Anne', id: 5, scores: 100 },
        { name: 'Chris', id: 6, scores: 93 },
        { name: 'Mike', id: 7, scores: 84 }
      ],
      numbers: [1, 2, 3, 4, 5]
    }
  },
  methods: {
    reverse() {
      this.msg = this.msg.split('').reverse().join('')
    },
    append() {
      this.msg = this.msg += '!'
    },
    toggleRed() {
      this.isRed = !this.isRed
    },
    toggleColor() {
      this.color = this.color === 'green' ? 'blue' : 'green'
    },
    sortByScores() {
      this.values = this.values.sort((a, b) => b.scores - a.scores)
    },
    getEvenNumbers() {
      this.numbers = this.numbers.filter((number) => number % 2 === 0)
    }
  }
}
</script>
<style scoped>
.red {
  color: red;
}
.Large {
  font-size: large;
}
table,
th,
td {
  border: 1px solid black;
  border-collapse: collapse;
}
</style>
