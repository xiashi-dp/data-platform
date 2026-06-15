import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import ReportView from '../views/ReportView.vue'
import VideoPvView from '../views/VideoPvView.vue'

const routes = [
    { path: '/', redirect: '/login' },
    { path: '/login', name: 'login', component: LoginView },
    { path: '/report', name: 'report', component: ReportView },
    { path: '/video', name: 'video', component: VideoPvView },
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

// 路由守卫：没登录不能进
router.beforeEach((to, from, next) => {
    const token = localStorage.getItem('token')
    const needAuth = to.path === '/report' || to.path === '/video'
    if (needAuth && !token) {
        next('/login')
    } else {
        next()
    }
})

export default router
