import axios  from "axios";
import router from "@/router/index.js";


// 设置动态路由
export const setupDynamicRoutes = (menuData) => {
    if (typeof menuData !== 'object' || menuData === null) {
        console.error("Invalid menu data format:", menuData);
        return;
    }

    Object.keys(menuData).forEach(menuKey => {
        const subMenus = menuData[menuKey];
        if (subMenus && typeof subMenus === 'object') {
            Object.keys(subMenus).forEach(subMenuKey => {
                const componentPath = subMenus[subMenuKey];
                router.addRoute('layout', {
                    path: `/${menuKey}/${subMenuKey}`,
                    name: `${menuKey}-${subMenuKey}`,
                    component: () => import(`../${componentPath}.js`)
                });
            });
        }
    })
}


//初始化
var instance = axios.create({
    baseURL: 'http://localhost:5173/api/',
    timeout: 1000,
});
//添加请求拦截器
instance.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    console.log("发起请求")
    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});
//添加响应拦截器
instance.interceptors.response.use(function (response) {
    console.log("响应请求")
    return response;
}, function (error) {
    // 对响应错误做点什么
    return Promise.reject(error);
});

//将token信息添加到请求头中
export  const  $setToken=(token)=>{
    instance.defaults.headers.common['token'] = token;
}

//get方法
export const $get =async (url,params)=>{
    let {data} = await instance.get(url,{
        params
    })
    return data;
}
//post方法
export const $post =async (url,params)=>{
    let {data}= await instance.post(url,params);
    return data;
}
//put方法
export const $put =async (url,params)=>{
    let {data}= await instance.put(url,params);
    return data;
}
//delete方法
export const $delete =async (url,params)=>{
    let {data}= await instance.delete(url,{
        params
    });
    return data;
}