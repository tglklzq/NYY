import {createRouter, createWebHistory} from 'vue-router'


const routes = [
    {
        path: '/',
        name: 'login',
        meta: {
            title: '农益友管理系统'
        },
        icon: 'el-icon-s-home',
        component: () => import('../views/Login.vue')
    },
    {
        path: '/layout',
        name: 'layout',
        meta: {
            title: '农益友管理系统',
        },
        component: () => import('../views/Layout.vue'),
        children: [
            {
                path: '',
                name: 'home',
                title: '首页',
                component: () => import('../views/Home.vue'),
            },
            {
                path: '/mail',
                name: 'mail',
                title: '邮件',
                component: () => import('../views/Mail.vue'),
            },
            {
                path: '/message',
                name: 'message',
                title: '消息',
                component: () => import('../views/Message.vue'),
            },
        ]
    },
    {
        path: '/:pathMatch(.*)*',
        name: '404',
        meta: {
            title: '404'
        },
        component: () => import('../views/Error404.vue')
    }
]
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})
router.beforeEach((to, from, next) => {
    if (to.path === '/layout') {
        let user = sessionStorage.getItem('user');
        if (!user) {
            router.push('/');
        }
    }
    next();
})
//后置守卫
router.afterEach((to, from, next) => {
    console.log(to)
    document.title = to.meta.title;
})

export default router
