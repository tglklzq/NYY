import {$get, $post} from "@/utils/request.js";
import {message} from "ant-design-vue";

await $post('user/showAllusers',params);
export  const $adminsTable = async (params) => {
    let {statusCode, success, message: statusMessage, data, token}  = await $get('user/showAllusers', params);
    if (success) {
        message.success(statusMessage);
        console.log(JSON.stringify(data));
    } else {
        message.error(statusMessage);
    }
}