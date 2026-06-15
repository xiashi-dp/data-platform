<template>
  <div id="app">
    <div v-if="isLogin">
      <nav>
        <el-menu mode="horizontal" router>
          <el-menu-item index="/report">📊 运营报表</el-menu-item>
          <el-menu-item index="/video">📺 视频分析</el-menu-item>
          <div style="float: right; line-height: 60px; margin-right: 20px; cursor: pointer; color: #409eff" @click="logout">
            退出登录
          </div>
        </el-menu>
      </nav>
      <hr>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  data() {
    return {
      isLogin: false,
    }
  },
  methods: {
    checkLogin() {
      this.isLogin = !!localStorage.getItem('token')
    },
    logout() {
      localStorage.removeItem('token')
      localStorage.removeItem('username')
      this.isLogin = false
      this.$router.push('/login')
    }
  },
  mounted() {
    this.checkLogin()
  },
  watch: {
    $route() {
      this.checkLogin()
    }
  }
}
</script>
