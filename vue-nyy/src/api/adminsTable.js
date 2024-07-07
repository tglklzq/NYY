import {$get, $post} from "@/utils/request.js";

export let $adminsList = async (params) => {
    let res =await $get('user/showAllusers', params);
    if(res.success){
        return res.data;
    }
}

export  let $personalInformation = async (params) => {
    let res =await $post('user/personalInformation', params);
    if(res.success){
        return res.data;
    }
}


export  let $log = async (params) => {
    let res =await $get('user/logs', params);
    if(res.success){
        console.log("日志获取成功")
        return res.data;
    }
}