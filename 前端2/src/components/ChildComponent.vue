<template>
  <div>
    <div>This is a child Component</div>
    <span>This is props title</span>
    {{ title }}
    <br />
    props value is {{ name }}
    <button @click="clickBtn()"></button>
  </div>
</template>
<script>
export default {
  expose: ['publicData', 'publicMethod'],
  emits: ['some-event'],
  // props: ['title','name'],
  props: {
    // dafault:"there",
    title: String,
    name: String
  },
  data() {
    return {
      publicData: 'foo',
      privateData: 'bar'
    }
  },
  mounted() {
    // this.$refs.child will hold an instance of <Child />
  },
  methods: {
    publicMethod() {
      /* ... */
    },
    privateMethod() {
      /* ... */
    },
    clickBtn() {
      console.log('handle button')
      this.$emit('some-event')
    }
  }
}
</script>
