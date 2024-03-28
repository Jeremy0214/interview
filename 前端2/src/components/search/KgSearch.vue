<template>
  <a-card>
    <a-form ref="formRef" name="advanced_search" class="ant-advanced-search-form" :model="form">
      <a-row :gutter="24">
        <slot></slot>
      </a-row>
      <a-row>
        <a-col :span="24" style="text-align: right">
          <!-- <a-button @click.prevent="query()" type="primary"> 查詢 </a-button> -->
          <a-button style="margin: 0 8px" @click="() => resetForm()"> 重設 </a-button>
          <slot name="extraBtn"></slot>
        </a-col>
      </a-row>
    </a-form>
  </a-card>
</template>
<script>
export default {
  name: 'KgSearch',
  components: {},
  data() {
    return {
      form: {}
    }
  },
  methods: {
    resetForm() {
      this.$emit('reset')
    },
    query() {
      this.$emit('handle-query', this.form)
    }
  }
}
</script>
<style scoped></style>
