<template>
  <a-modal
    v-model:open="open"
    :title="modalConfig.title"
    :ok-text="modalConfig.okText"
    :cancel-text="modalConfig.cancelText"
    :closable="modalConfig.closable"
    @ok="okClick()"
    @cancel="cancelClick()"
    @close="hideModal()"
  >
    <p>{{ modalConfig.content }}</p>
  </a-modal>
</template>

<script>
export default {
  name: 'KgDialog',
  components: {},
  props: {},
  data() {
    return {
      open: false,
      resolve: null,
      reject: null,
      modalConfig: {
        title: '確認',
        content: 'Some descriptions',
        okText: '是',
        cancelText: '否',
        closable: false
      }
    }
  },
  methods: {
    showConfirm({ title, content, okText, cancelText, closable } = {}) {
      // console.log("showConfirm", title);
      this.modalConfig.title = title || this.modalConfig.title
      this.modalConfig.content = content || this.modalConfig.content
      this.modalConfig.okText = okText || this.modalConfig.okText
      this.modalConfig.cancelText = cancelText || this.modalConfig.cancelText
      this.modalConfig.closable = closable || this.modalConfig.closable

      this.showModal()
      return new Promise((resolve, reject) => {
        this.resolve = resolve
        this.reject = reject
      })
    },
    okClick() {
      // console.log('okClick');
      this.resolve(true)
      this.hideModal()
    },
    cancelClick() {
      // console.log('cancelClick');
      this.resolve(false)
      this.hideModal()
    },
    showModal() {
      this.open = true
    },
    hideModal() {
      this.open = false
    }
  }
}
</script>
<style scoped></style>
