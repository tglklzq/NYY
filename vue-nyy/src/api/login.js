import {$post,$setToken} from  '../utils/request'
import {message} from "ant-design-vue";
// 登录接口
export const $login = async (params) => {
      let {statusCode, success, message: statusMessage, data,token}  = await $post('user/login',params);
      if(success){
            //将token保存到浏览器的缓存中
            sessionStorage.setItem('token',token);
            //在请求头中更新token信息
            $setToken(token);
            console.log(token);
            message.success(statusMessage);
      }else{
            message.error(statusMessage);
      }
      return success;

}
export  const $register = async (params) => {
      let {statusCode, success, message: statusMessage, data,token} = await $post('user/register',params);
      if(success){
            message.success(statusMessage);
      }else{
            message.error(statusMessage);
      }
}