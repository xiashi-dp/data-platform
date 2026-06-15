import axios from 'axios'

// 创建 axios 实例
const http = axios.create()

// 请求拦截器 —— 每次请求自动带上 Token
http.interceptors.request.use(config => {
    const token = localStorage.getItem('token')
    if (token) {
        config.headers.Authorization = 'Bearer ' + token
    }
    return config
})

export default http
