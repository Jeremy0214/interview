<template>
  <body>
    <!-- 10 -->
    <span>{{ count }}</span
    ><br />
    <button @click="count++">add</button>
    <button @click="count--">subtract</button>
    <button @click="clear">clear</button><br />
    <input v-model="number" />
    <button @click="multiple">multiple</button>
    <!-- 11 -->
    <h2>Text Input</h2>
    <input v-model="text" />{{ text }}
    <h2>Checkbox</h2>
    <input type="checkbox" id="checkbox" v-model="checked" />
    <label for="checkbox">Checked: {{ checked }}</label>
    <h2>Multi Checkbox</h2>
    <input type="checkbox" id="jack" value="Jack" v-model="checkedNames" />
    <label for="jack">Jack</label>
    <input type="checkbox" id="john" value="John" v-model="checkedNames" />
    <label for="john">John</label>
    <input type="checkbox" id="mike" value="Mike" v-model="checkedNames" />
    <label for="mike">Mike</label><br />
    <span
      >Checked names:
      <pre>{{ checkedNames }}</pre>
    </span>
    <h2>Radio</h2>
    <input type="radio" id="one" value="One" v-model="picked" />
    <label for="one">One</label>
    <br />
    <input type="radio" id="two" value="Two" v-model="picked" />
    <label for="two">Two</label>
    <p>Picked:{{ picked }}</p>
    <h2>Select</h2>
    <select v-model="selected">
      <option disabled value="">Please choose one</option>
      <option>A</option>
      <option>B</option>
      <option>C</option>
    </select>
    <span>Selected:{{ selected }}</span>
    <h2>Multi Select</h2>
    <select v-model="multiSelected" multiple style="width: 100px">
      <option>A</option>
      <option>B</option>
      <option>C</option>
    </select>
    <span>Selected: {{ multiSelected }}</span>
    <div>
      <input type="number" v-model="phoneNumber" />
      <p v-if="!verificationResult">請輸入正確格式</p>
    </div>
  </body>
</template>
<script>
export default {
  data() {
    return {
      count: 0,
      phoneNumber: '',
      number: '',
      verificationResult: true,
      text: 'Edit me',
      checked: true,
      checkedNames: ['Jack'],
      picked: 'One',
      selected: 'A',
      multiSelected: ['A']
    }
  },
  methods: {
    clear() {
      this.count = 0
    },
    multiple() {
      this.count *= this.number
    }
  },
  watch: {
    // whenever number changes, this function will run
    phoneNumber: function (newNumber) {
      this.verificationResult = /^09[0-9]{8}$/.test(newNumber)
    }
  }
}
</script>
<style scoped>
body {
  font-size: 25px;
  text-align: center;
}
h1 {
  color: lightslategray;
}
</style>
