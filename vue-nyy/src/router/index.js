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
                component: () => import('../views/Horizontal_Router/Home.vue'),
            },
            {
                path: '/mail',
                name: 'mail',
                title: '邮件',
                component: () => import('../views/Horizontal_Router/Mail.vue'),
            },
            {
                path: '/message',
                name: 'message',
                title: '消息',
                component: () => import('../views/Horizontal_Router/Message.vue'),
            },
            {
                path: '/personalInformation',
                name: 'personalInformation',
                title: '个人中心',
                component: () => import('../views/Horizontal_Router/PersonalInformation.vue'),
            },
            {
                path: '/changePassword',
                name: 'changePassword',
                title: '修改密码',
                component: () => import('../views/Horizontal_Router/ChangePassword.vue'),
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
