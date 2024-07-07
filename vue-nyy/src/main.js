import  {createApp} from 'vue'
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
let mineMenus = JSON.parse(sessionStorage.getItem('mineMenus')) || [];
console.log("这是main.js的内容："+JSON.stringify(mineMenus));
if(mineMenus){
    mineMenus.forEach(menu => {
        menu.children.forEach(item => {
            const [directory, filename] = item.component.split('/'); // 拆分目录名和文件名
            router.addRoute('layout',{
                path: item.path,
                name: item.name,
                meta: { title: item.title },
                component:  () => import(`@/views/Vertical_router/${directory}/${filename}.vue`),
            });
        });
    });
}


const app = createApp(App);



app.use(Antd).use(router).mount('#app')

