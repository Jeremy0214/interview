<template>
  <!-- <child-component></child-component> -->
  <div>
    <ChildComponent
      v-for="post in posts"
      :key="post.id"
      :title="post.title"
      :name="val"
      @some-event="fromChildEvent()"
    ></ChildComponent>
    <ChildComponent :name="val" />
  </div>
</template>
<script>
import ChildComponent from '@/components/ChildComponent.vue'
import HelloWorld from '@/components/HelloWorld.vue'
export default {
  components: {
    ChildComponent,HelloWorld
  },
  data() {
    return {
      val: 'val variable',
      posts: [
        { id: 1, title: 'My journey with Vue' },
        { id: 2, title: 'Blogging with Vue' },
        { id: 3, title: 'Why Vue is so fun' }
      ]
    }
  },
  mounted() {
    // this.$refs.child will hold an instance of <Child />
    // console.log(this.$refs.child.publicData)
    // console.log(this.$refs.child.privateData)
  },
  methods: {
    fromChildEvent() {
      console.log('from child')
    }
  }
}
</script>
