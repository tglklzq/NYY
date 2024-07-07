<template>
  <a-table :dataSource="dataSource" :columns="columns" size="small" :pagination="false"/>
</template>

<script setup>
import {ref} from 'vue';
import {$adminsList} from "@/api/adminsTable.js";


// 统一定义当前组件的数据
const dataSource = ref([]);

const columns = ref([
  {
    title: '用户名',
    dataIndex: 'username',
    key: 'username',
  },
  {
    title: '邮箱',
    dataIndex: 'email',
    key: 'email',
  },
  {
    title: '角色权限',
    dataIndex: 'role_name',
    key: 'role_name',
  },
  {
    title: '手机号码',
    dataIndex: 'phone_number',
    key: 'phone_number',
  },
]);
// 加载数据的异步函数
const loadData = async () => {
  try {
    dataSource.value = await $adminsList({pageIndex: 1, pageSize: 10, name: ''}); // 更新响应式状态
  } catch (error) {
    console.error('加载数据失败:', error);
    // 错误处理逻辑
  }
};
loadData();
</script>