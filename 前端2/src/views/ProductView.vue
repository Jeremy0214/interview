<template>
  <div class="about">
    <kg-search @reset="resetSearchForm()" @handle-query="handleQuery">
      <template v-for="i in 1" :key="i">
        <a-col :span="8">
          <a-form-item label="分類" name="category">
            <a-space direction="vertical">
              <a-select
                v-model:value="formState"
                style="width: 200px"
                :options="options"
              ></a-select>
            </a-space>
          </a-form-item>
          <a-button @click="handleQuery" type="primary"> 查詢 </a-button>
        </a-col>
      </template>
      <template #extraBtn>
        <a-button style="background-color: #004e6d; color: #fff" @click="insertBtnClick">
          新增
        </a-button>
      </template>
    </kg-search>
    <KgTable
      :columns="columns"
      :data="tableData"
      :loading="loading"
      :tablePage="tablePage"
      @row-delete="handleRowDelete"
      @row-update="handleRowUpdate"
      @page-size-change="handlePageSizeChange"
    >
    </KgTable>
    <kg-dialog ref="confirmDialog"> </kg-dialog>
    <a-modal
      v-model:open="detailOpen"
      :title="dialogType === 'I' ? '新增' : '修改'"
      width="70%"
      ok-text="確認"
      cancel-text="取消"
      @ok="okClick"
      @cancel="cancelClick"
      :closable="false"
    >
      <a-form ref="formRef" :layout="inputForm.layout" :model="inputForm" :rules="rules">
        <a-form-item label="產品名稱" name="name">
          <a-input v-model:value="inputForm.productName" placeholder="productName" />
        </a-form-item>
        <a-form-item label="分類標籤" name="category">
          <a-space direction="vertical">
            <a-select
              v-model:value="inputForm.category"
              style="width: 200px"
              :options="options"
            ></a-select>
          </a-space>
        </a-form-item>
        <a-form-item label="價格" name="price">
          <a-textarea v-model:value="inputForm.price" />
        </a-form-item>
        <a-form-item label="庫存" name="stock">
          <a-textarea v-model:value="inputForm.stock" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script>
import { DownOutlined, UpOutlined } from '@ant-design/icons-vue'
import KgSearch from '@/components/search/KgSearch.vue'
import KgDialog from '@/components/KgDialog.vue'
import api from '@/service/product.js'
import { message } from 'ant-design-vue'
import KgTable from '@/components/table/KgTable.vue'
export default {
  name: 'ProductView',
  components: {
    KgSearch,
    KgTable,
    DownOutlined,
    UpOutlined,
    KgDialog
  },
  data() {
    return {
      formState: '',
      expand: false,
      open: true,
      detailOpen: false,
      isReset: false,
      options: [
        {
          value: 'FOOD',
          label: 'FOOD'
        },
        {
          value: 'CAR',
          label: 'CAR'
        }
      ],
      columns: [
        {
          title: '訂單編號',
          dataIndex: 'productId',
          width: 30,
          resizable: true
        },
        {
          title: '產品名稱',
          dataIndex: 'productName',
          width: 70,
          type: 'detail',
          onDetailClick: function (record) {
            console.log('detail', record)
          }
        },
        {
          title: '分類標籤',
          dataIndex: 'category',
          key: 'tags',
          width: 50
        },
        {
          title: '價格',
          dataIndex: 'price',
          width: 100
        },
        {
          title: '庫存',
          dataIndex: 'stock',
          width: 100
        },
        {
          title: '操作',
          key: 'action',
          width: 150,
          methods: [
            {
              title: '檢視',
              actionFunc: function () {
                console.log('detail')
              }
            }
          ]
        }
      ],
      tableData: [],
      loading: false,
      dialogType: 'I',
      inputForm: {
        id: null,
        productName: null,
        imageUrl: '123',
        category: '',
        price: null,
        stock: null
      },
      rules: {
        productName: [
          {
            required: true,
            message: '必填欄位',
            trigger: 'blur'
          }
        ]
      },
      tablePage: {
        current: 1,
        pageSize: 10
      }
    }
  },
  created: function () {
    this.handleQuery()
  },
  methods: {
    resetSearchForm() {
      this.formState = this.$options.data().formState
      this.handleQuery(true)
    },
    formReset() {
      console.log(this.$options.data().inputForm)
      this.inputForm = this.$options.data().inputForm
    },
    handleRowUpdate(record) {
      console.log('update', record)
      this.detailOpen = true
      this.dialogType = 'U'
      Object.keys(record).forEach((key) => {
        this.inputForm[key] = record[key]
      })
    },
    async handleRowDelete(record) {
      console.log('delete', record)
      const res = await this.$refs.confirmDialog.showConfirm({
        title: '確認刪除',
        content: '確定要刪除' + record.productName + '嗎?',
        okText: '確認',
        cancelText: '取消'
      })
      console.log('res', res)
      // click OK
      if (res) {
        console.log('delete', record)
        await api
          .delete(record.productId)
          .then((response) => {
            this.loading = false
            console.log('success', response)
            message.open({
              type: 'success',
              content: '刪除成功'
            })
            this.formState = this.$options.data().formState
            this.handleQuery(true)
          })
          .catch((error) => {
            this.loading = false
            console.log('error', error)
            message.open({
              type: 'error',
              content: error.message
            })
          })
      }
    },
    async handleQuery(isReset) {
      if (isReset) {
        this.tablePage.current = 1
      }
      console.log('query', this.formState)
      this.loading = true
      await api
        .getAll(this.tablePage.current, this.tablePage.pageSize, this.formState)
        .then((response) => {
          this.loading = false
          console.log('success', response)
          this.tableData = response.data
        })
        .catch((error) => {
          this.loading = false
          console.log('error', error)
          message.open({
            type: 'error',
            content: error.message
          })
        })
    },
    handlePageSizeChange(pageObj) {
      this.tablePage.current = pageObj.current
      this.tablePage.pageSize = pageObj.pageSize
      this.handleQuery(false)
    },
    insertBtnClick() {
      this.detailOpen = true
      this.dialogType = 'I'
      this.formReset()
    },
    okClick() {
      console.log('okClick')
      console.log(this.$refs.formRef)
      this.$refs.formRef
        .validate()
        .then(() => {
          console.log('valid', this.inputForm)
          this.detailOpen = false
          if (this.dialogType === 'I') {
            api
              .insert(this.inputForm)
              .then((response) => {
                console.log('success', response)
                message.open({
                  type: 'success',
                  content: '新增成功'
                })
                // reload
                this.formState = this.$options.data().formState
                this.handleQuery(true)
              })
              .catch((error) => {
                console.log('error', error)
                message.open({
                  type: 'error',
                  content: error.message
                })
              })
          } else {
            api
              .update(this.inputForm, this.inputForm.productId)
              .then((response) => {
                console.log('success', response)
                message.open({
                  type: 'success',
                  content: '修改成功'
                })
                // reload
                this.formState = this.$options.data().formState
                this.handleQuery(true)
              })
              .catch((error) => {
                console.log('error', error)
                message.open({
                  type: 'error',
                  content: error.message
                })
              })
          }
        })
        .catch((error) => {
          console.log('error', error)
        })
    },
    cancelClick() {
      console.log('cancelClick')
      this.detailOpen = false
    }
  }
}
</script>
<style scoped></style>
