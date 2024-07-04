import {createRouter, createWebHistory} from 'vue-router'


const routes = [
    {
        path: '/',
        name: 'login',
        component: () => import('../views/Login.vue')
    },
    {
        path: '/layout',
        name: 'layout',
        component: () => import('../views/Layout.vue'),
    },
]
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})
router.beforeEach((to, from, next)=>{
    if(to.path==='/layout'){
        let user = sessionStorage.getItem('user');
        if(!user){
            router.push('/');
        }
    }
    next();
})
router.afterEach((to, from, next)=>{
    console.log(to)
})

export default router
