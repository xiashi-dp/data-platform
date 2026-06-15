<template>
  <div style="padding: 20px">
    <h2>📺 视频分析报表</h2>
    <p style="color: #909399; margin-bottom: 20px">
      数据来源：Kafka → Flink 实时计算 → ClickHouse
    </p>

    <!-- 查询条件 -->
    <el-row :gutter="16" style="margin-bottom: 20px">
      <el-col :span="6">
        <el-date-picker v-model="startDate" type="datetime" placeholder="起始时间"
                         value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
      </el-col>
      <el-col :span="6">
        <el-date-picker v-model="endDate" type="datetime" placeholder="截止时间"
                         value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
      </el-col>
      <el-col :span="6">
        <el-button type="primary" @click="loadData">查询</el-button>
        <el-button @click="resetAndLoad">重置</el-button>
      </el-col>
    </el-row>

    <!-- 汇总卡片 -->
    <el-row :gutter="16" style="margin-bottom: 20px">
      <el-col :span="6">
        <el-card shadow="hover">
          <div style="text-align: center">
            <div style="font-size: 28px; font-weight: bold; color: #409eff">{{ totalPv }}</div>
            <div style="color: #909399; font-size: 14px">总播放量 (PV)</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div style="text-align: center">
            <div style="font-size: 28px; font-weight: bold; color: #67c23a">{{ totalUv }}</div>
            <div style="color: #909399; font-size: 14px">总访客数 (UV)</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div style="text-align: center">
            <div style="font-size: 28px; font-weight: bold; color: #e6a23c">{{ totalVideos }}</div>
            <div style="color: #909399; font-size: 14px">视频总数</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div style="text-align: center">
            <div style="font-size: 28px; font-weight: bold; color: #f56c6c">{{ avgPv }}</div>
            <div style="color: #909399; font-size: 14px">平均播放量</div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 图表区域 -->
    <el-row :gutter="16" style="margin-bottom: 20px">
      <el-col :span="14">
        <el-card>
          <template #header><b>📊 视频播放量排行</b></template>
          <div ref="barChart" style="height: 350px"></div>
        </el-card>
      </el-col>
      <el-col :span="10">
        <el-card>
          <template #header><b>🥧 播放量占比</b></template>
          <div ref="pieChart" style="height: 350px"></div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 视频排行表格 -->
    <el-card>
      <template #header>
        <b>🎬 详细数据</b>
      </template>
      <el-table :data="list" border stripe v-loading="loading"
                empty-text="暂无数据" @sort-change="onSortChange">
        <el-table-column type="index" label="排名" width="60" />
        <el-table-column prop="videoId" label="视频ID" width="100" />
        <el-table-column prop="pv" label="播放量 (PV)" width="120" sortable="custom" />
        <el-table-column prop="uv" label="访客数 (UV)" width="120" sortable="custom" />
        <el-table-column prop="windowEnd" label="最近窗口" min-width="160" />
      </el-table>
    </el-card>

    <el-alert v-if="error" :title="error" type="error" show-icon
              style="margin-top: 16px" closable @close="error = ''" />
  </div>
</template>

<script>
import http from '../utils/http'
import * as echarts from 'echarts'

export default {
  data() {
    return {
      startDate: '',
      endDate: '',
      list: [],
      loading: false,
      error: '',
      barInstance: null,
      pieInstance: null,
    }
  },
  computed: {
    totalPv() { return this.list.reduce((s, r) => s + r.pv, 0) },
    totalUv() { return this.list.reduce((s, r) => s + r.uv, 0) },
    totalVideos() { return this.list.length },
    avgPv() {
      return this.list.length === 0 ? 0 : Math.round(this.totalPv / this.list.length)
    }
  },
  methods: {
    loadData() {
      this.loading = true
      this.error = ''
      const params = { limit: 20 }
      if (this.startDate) params.startDate = this.startDate
      if (this.endDate) params.endDate = this.endDate
      http.get('/api/video/pv', { params })
        .then(res => {
          if (res.data.code === 200) {
            this.list = res.data.data
            this.$nextTick(() => this.renderCharts())
          } else {
            this.error = res.data.message
          }
        }).catch(() => {
          this.error = '请求失败'
        }).finally(() => {
          this.loading = false
        })
    },
    resetAndLoad() {
      this.startDate = ''
      this.endDate = ''
      this.loadData()
    },
    onSortChange({ prop, order }) {
      if (!prop || !order) return
      this.list.sort((a, b) => {
        return order === 'ascending' ? a[prop] - b[prop] : b[prop] - a[prop]
      })
    },
    renderCharts() {
      const ids = this.list.map(r => r.videoId).slice(0, 10)
      const pvs = this.list.map(r => r.pv).slice(0, 10)

      // 柱状图
      if (!this.barInstance) {
        this.barInstance = echarts.init(this.$refs.barChart)
      }
      this.barInstance.setOption({
        tooltip: { trigger: 'axis' },
        grid: { left: 50, right: 20, bottom: 40 },
        xAxis: { type: 'category', data: ids, axisLabel: { rotate: 30 } },
        yAxis: { type: 'value' },
        series: [{
          type: 'bar',
          data: pvs,
          itemStyle: {
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
              { offset: 0, color: '#409eff' }, { offset: 1, color: '#79bbff' }
            ])
          }
        }]
      })

      // 饼图
      if (!this.pieInstance) {
        this.pieInstance = echarts.init(this.$refs.pieChart)
      }
      this.pieInstance.setOption({
        tooltip: { trigger: 'item', formatter: '{b}: {c} ({d}%)' },
        series: [{
          type: 'pie',
          radius: ['30%', '60%'],
          data: this.list.slice(0, 8).map(r => ({ name: r.videoId, value: r.pv })),
          label: { show: true, formatter: '{b}' }
        }]
      })
    }
  },
  mounted() {
    this.loadData()
  },
  beforeUnmount() {
    this.barInstance?.dispose()
    this.pieInstance?.dispose()
  }
}
</script>
