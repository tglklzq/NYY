import {createRouter, createWebHistory} from 'vue-router'
import {generateRoutesFromMenu} from "@/router/dynamicRoutes.js";

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
        children: generateRoutesFromMenu(JSON.parse(sessionStorage.getItem('menu')) || {})
    },
]
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

export default router
