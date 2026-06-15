<template>
  <div style="padding: 20px">
    <h2>📊 运营报表</h2>

    <!-- 查询条件 -->
    <el-row :gutter="16" style="margin-bottom: 20px">
      <el-col :span="6">
        <el-date-picker v-model="startDate" type="date" placeholder="起始日期"
                         value-format="YYYY-MM-DD" style="width: 100%" />
      </el-col>
      <el-col :span="6">
        <el-date-picker v-model="endDate" type="date" placeholder="截止日期"
                         value-format="YYYY-MM-DD" style="width: 100%" />
      </el-col>
      <el-col :span="6">
        <el-button type="primary" @click="loadData">查询</el-button>
      </el-col>
    </el-row>

    <!-- 表格 -->
    <el-table :data="list" border stripe v-loading="loading"
              empty-text="暂无数据" style="width: 100%">
      <el-table-column prop="reportDate" label="日期" width="120" />
      <el-table-column prop="newUserCount" label="新增用户" width="120" />
      <el-table-column prop="activeUserCount" label="活跃用户" width="120" />
      <el-table-column prop="totalOrderCount" label="总订单数" width="120" />
      <el-table-column prop="totalOrderAmount" label="总订单金额" min-width="140" />
      <el-table-column prop="avgOrderAmount" label="平均金额" width="120" />
      <el-table-column prop="totalProductCount" label="上架商品" width="120" />
      <el-table-column prop="newProductCount" label="新增商品" width="120" />
    </el-table>

    <!-- 错误提示 -->
    <el-alert v-if="error" :title="error" type="error" show-icon
              style="margin-top: 16px" closable @close="error = ''" />
  </div>
</template>

<script>
import http from '../utils/http'

export default {
  data() {
    return {
      startDate: '2026-06-01',
      endDate: '2026-06-07',
      list: [],
      loading: false,
      error: '',
    }
  },
  methods: {
    loadData() {
      this.loading = true
      this.error = ''
      http.get('/api/report/range', {
        params: { startDate: this.startDate, endDate: this.endDate }
      }).then(res => {
        if (res.data.code === 200) {
          this.list = res.data.data
        } else {
          this.error = res.data.message
        }
      }).catch(err => {
        this.error = '请求失败：' + err.message
      }).finally(() => {
        this.loading = false
      })
    }
  },
  mounted() {
    this.loadData()
  }
}
</script>
