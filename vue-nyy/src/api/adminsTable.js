import {$post} from "@/utils/request.js";

export let $adminsList = async (params) => {
    let res =await $post('user/showAllusers', params);
    if(res.success){
        return res.data;
    }
    //return await $post('user/showAllusers', params);
}

