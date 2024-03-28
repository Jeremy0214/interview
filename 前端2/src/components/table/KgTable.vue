<template>
  <div class="tableContainer" ref="tableContainer"  :style="{ height: containerH + 'px' }">
    <a-card>
      <a-table
        :columns="columns"
        :data-source="data.result"
        :scroll="{ y: countHeight }"
        :loading="loading"
        :pagination="pagination"
        @change="onShowSizeChange"
        tableLayout="fixed"
      >
        <template #headerCell="{ column }">
          <span>
            {{ column.title }}
          </span>
        </template>

        <template #bodyCell="{ column, record }">
          <template v-if="column.type === 'detail'">
            <a @click="column.onDetailClick(record)">
              {{ record[column.dataIndex] }}
            </a>
          </template>
          <template v-else-if="column.key === 'tags'">
            <span>
              <a-tag :color="record.category === 'CAR' ? 'geekblue' : 'green'">
                {{ record.category }}
              </a-tag>
            </span>
          </template>
          <template v-else-if="column.key === 'action'">
            <span>
              <a @click="rowUpdate(record)">修改</a>
              <a-divider type="vertical" />
              <a @click="rowDelete(record)">刪除</a>
              <a-divider type="vertical" />

              <a-dropdown v-if="!!column.methods && column.methods.length > 0">
                <a class="ant-dropdown-link"> 更多 </a>
                <template #overlay>
                  <a-menu>
                    <a-menu-item v-for="item in column.methods" v-bind:key="item">
                      <a @click="item.actionFunc">
                        {{ item.title }}
                      </a>
                    </a-menu-item>
                  </a-menu>
                </template>
              </a-dropdown>
            </span>
          </template>
        </template>
      </a-table>
    </a-card>
  </div>
</template>
<script>
export default {
  name: 'KgTable',
  components: {},
  props: {
    columns: {
      type: Array,
      required: true
    },
    data: {
      type: Object,
      required: true
    },
    loading: {
      type: Boolean,
      default: false
    },
    tablePage: {
      current: 1,
      pageSize: 10
    }
  },
  data() {
    return {
      form: {},
      countHeight: 300,

      containerH: 300
    }
  },
  computed: {
    totalItemsNum() {
      // console.log("totalItemsNum", this.data.totalItems);
      return this.data.total
    },
    pagination() {
      return {
        total: this.totalItemsNum,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: () => `共 ${this.totalItemsNum} 筆資料`,
        pageSizeOptions: ['5', '10', '20', '50'],
        pageSize: this.tablePage.pageSize,
        current: this.tablePage.current
      }
    }
  },
  methods: {
    rowUpdate(record) {
      // console.log("rowUpdate", record);
      this.$emit('row-update', record)
    },
    rowDelete(record) {
      // console.log("rowDelete", record);
      this.$emit('row-delete', record)
    },
    onShowSizeChange(pageObj) {
      // console.log("onShowSizeChange", pageObj);
      this.$emit('page-size-change', pageObj)
    }
  },
  mounted() {
    const padding = 120
    // window總高度
    const windowH = window.innerHeight

    // table 最高點
    let table = this.$refs.tableContainer
    console.log(table)
    let tableTop = table?.getBoundingClientRect().top ?? 0
    let tableHeight = table ? table.offsetHeight : 0
    // 計算剩餘高度
    let tableDataHeight = table ? table.querySelector('.ant-table-wrapper')?.offsetHeight : 0
    this.countHeight = windowH - tableTop - (tableHeight - tableDataHeight) - padding
    this.containerH = windowH - tableTop
    // console.log(windowH+"-"+ tableTop+"-("+tableHeight+")="+ this.countHeight);
    // console.log(
    //   windowH +
    //     '-' +
    //     tableTop +
    //     '-(' +
    //     tableHeight +
    //     '-' +
    //     tableDataHeight +
    //     ')=' +
    //     this.countHeight
    // )
  }
}
</script>
<style scoped>
.ant-card {
  margin: 0;
  padding: 0;
  height: 100%;
}
</style>
