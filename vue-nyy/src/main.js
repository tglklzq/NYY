import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios';
// 引用ant-desgin

import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
import '@ant-design/icons';



const app = createApp(App)
app.use(Antd).use(router).mount('#app')
app.provide('axios', axios);