<template>
  <div>
  <div>
    {{ formatDate() }}
    <a :href="url">連結</a>
    <div :style="{ color: activeColor, fontSize: fontSize + 'px' }">Hello</div>
  </div>
  <div v-if="type === 'A'">A</div>
  <div v-else-if="type === 'B'">B</div>
  <div v-else-if="type === 'C'">C</div>
  <div v-else>Not A/B/C</div>
  <li v-for="(item, index) in items" :key="item.id">{{ parentMessage }} - {{ index }} - {{ item.message }}</li>
  <li v-for="item in items" :key="item.id">
    <span v-for="childItem in item.children" :key="childItem.id"> {{ item.message }}{{ childItem.message }} </span>
  </li>
  <li v-for="(value, key) in myObject" :key="value">{{ key }}: {{ value.message }}</li>
  <p>Message is: {{ message }}</p>
  <input v-model="message" placeholder="edit me" />

  <span>Multiline message is:</span>
  <p style="white-space: pre-line">{{ message1 }}</p>
  <textarea v-model="message1" placeholder="add multiple lines"></textarea>
  <input type="checkbox" id="checkbox" v-model="checked" />
  <label for="checkbox"> value:{{ checked }}</label>
  <div>Checked names: {{ checkedNames }}</div>
  <input type="checkbox" id="jack" value="Jack" v-model="checkedNames" />
  <label for="jack">Jack</label>

  <input type="checkbox" id="john" value="John" v-model="checkedNames" />
  <label for="john">John</label>

  <input type="checkbox" id="mike" value="Mike" v-model="checkedNames" />
  <label for="mike">Mike</label>

  <div>Picked: {{ picked }}</div>

  <input type="radio" id="one" value="One" v-model="picked" />
  <label for="one">One</label>

  <input type="radio" id="two" value="Two" v-model="picked" />
  <label for="two">Two</label>

  <select v-model="selected">
    <option v-for="option in options" :value="option.value"  :key="option">
      {{ option.text }}
    </option>
  </select>

  <div>Selected: {{ selected }}</div>
  <input type="radio" v-model="pick" :value="first" />
  <input type="radio" v-model="pick" :value="second" />
  <p>
    Ask a yes/no question:
    <input v-model="question" :disabled="loading" />
  </p>
  <p>{{ answer }}</p>
</div>
</template>
<script>
export default {
  data() {
    return {
      url: 'https://tw.yahoo.com/',
      activeColor: 'red',
      fontSize: 30,
      type: 'A',
      parentMessage: 'Parent',
      items: [{ message: 'Foo' }, { message: 'Bar' }],
      items: [
        {
          message: 'A',
          children: [{ message: 'a' }, { message: 'b' }, { message: 'c' }]
        }
      ],
      myObject: [{ message: 'Foo', message: 'Bar' }],
      message: '',
      message1: '',
      checked: true,
      checkedNames: [],
      picked: [],
      selected: 'A',
      options: [
        { text: 'One', value: 'A' },
        { text: 'Two', value: 'B' },
        { text: 'Three', value: 'C' }
      ],
      pick: [],
      question: '',
      answer: 'Questions usually contain a question mark. ;-)',
      loading: false
    }
  },
  methods: {
    formatDate() {
      return new Date().toLocaleDateString()
    },

    async getAnswer() {
      this.loading = true
      this.answer = 'Thinking...'
      try {
        const res = await fetch('https://yesno.wtf/api')
        this.answer = (await res.json()).answer
      } catch (error) {
        this.answer = 'Error! Could not reach the API. ' + error
      } finally {
        this.loading = false
      }
    }
  },
  watch: {
    // whenever question changes, this function will run
    question(newQuestion, oldQuestion) {
      if (newQuestion.includes('?')) {
        this.getAnswer()
      }
    }
  },
  async getAnswer() {
    this.loading = true
    this.answer = 'Thinking...'
    try {
      const res = await fetch('https://yesno.wtf/api')
      this.answer = (await res.json()).answer
    } catch (error) {
      this.answer = 'Error! Could not reach the API. ' + error
    } finally {
      this.loading = false
    }
  }
}
</script>
