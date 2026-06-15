<template>
  <div style="max-width: 400px; margin: 80px auto">
    <h2 style="text-align: center">🔐 数据平台登录</h2>

    <el-form :model="form" label-width="80px" style="margin-top: 30px">
      <el-form-item label="用户名">
        <el-input v-model="form.username" placeholder="请输入用户名" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password" type="password" placeholder="请输入密码"
                  show-password @keyup.enter="login" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="login" :loading="loading" style="width: 100%">
          登 录
        </el-button>
      </el-form-item>
    </el-form>

    <!-- 错误提示 -->
    <el-alert v-if="error" :title="error" type="error" show-icon
              closable @close="error = ''" />
  </div>
</template>

<script>
import http from '../utils/http'

export default {
  data() {
    return {
      form: { username: 'admin', password: 'admin123' },
      loading: false,
      error: '',
    }
  },
  methods: {
    login() {
      if (!this.form.username || !this.form.password) {
        this.error = '请输入用户名和密码'
        return
      }
      this.loading = true
      this.error = ''
      http.post('/api/user/login', null, {
        params: { username: this.form.username, password: this.form.password }
      }).then(res => {
        if (res.data.code === 200) {
          // 登录成功，保存 Token
          localStorage.setItem('token', res.data.data)
          localStorage.setItem('username', this.form.username)
          // 跳转到报表页面
          this.$router.push('/report')
        } else {
          this.error = res.data.message
        }
      }).catch(() => {
        this.error = '登录失败，请检查网络'
      }).finally(() => {
        this.loading = false
      })
    }
  }
}
</script>
