<template>
  <div>
    <TableComponent
      :headers="headers"
      :datas="values"
      @some-event="fromChildEvent"
      @click-url-event="childUrlEvent"
    >
      <img src="@/assets/spider.jpg" style="width: 30px" />
    </TableComponent>
  </div>
</template>

<script>
import TableComponent from '@/components/TableComponent.vue'
export default {
  components: {
    TableComponent
  },
  data() {
    return {
      headers: [
        { text: 'Scores', value: 'scores' },
        { text: 'ID', value: 'id' },
        { text: 'Name', value: 'name' }
      ],
      values: [
        { name: 'Andy', id: 2, scores: 98 },
        { name: 'David', id: 1, scores: 97 },
        { name: 'John', id: 3, scores: 100 },
        { name: 'Terry', id: 4, scores: 80 },
        { name: 'Anne', id: 5, scores: 100 },
        { name: 'Chris', id: 6, scores: 93 },
        { name: 'Mike', id: 7, scores: 84 }
      ]
    }
  },
  methods: {
    fromChildEvent(userInput) {
      console.log('btn from parent')
      alert('輸入的文字為:' + userInput)
    },
    childUrlEvent(data) {
      console.log(data)
    }
  }
}
</script>
