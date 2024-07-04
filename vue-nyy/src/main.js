import  {createApp} from 'vue'
import App from './App.vue'
import router from './router'



// 引用ant-desgin
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
import '@ant-design/icons';

//刷新时，检查是否需要更新token
import {$setToken, setupDynamicRoutes} from './utils/request'


let token = sessionStorage.getItem('token');
if (token) {
    $setToken(token);
}

const app = createApp(App);

const menuData = JSON.parse(sessionStorage.getItem('menu')) || {};
setupDynamicRoutes(menuData);

app.use(Antd).use(router).mount('#app')

