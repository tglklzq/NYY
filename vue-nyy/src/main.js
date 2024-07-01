import {createApp} from 'vue'
import App from './App.vue'
import router from './router'



// 引用ant-desgin
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
import '@ant-design/icons';

//刷新时，检查是否需要更新token
import {$setToken} from './utils/request'

let token = sessionStorage.getItem('token');
if (token) {
    $setToken(token);
}






const app = createApp(App)


app.use(Antd).use(router).mount('#app')
