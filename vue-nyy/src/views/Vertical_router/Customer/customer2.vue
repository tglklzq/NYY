<template>
  <a-table :dataSource="dataSource" :columns="columns" size="small" :pagination="false"/>

</template>

<script setup>
import { $log} from "@/api/adminsTable.js";
import {ref} from "vue";


const dataSource = ref([]);
const columns = ref([
  {
    title: 'Username',
    dataIndex: 'username',
    key: 'username',
  },
  {
    title: 'action',
    dataIndex: 'action',
    key: 'action',
  },
  {
    title: 'details',
    dataIndex: 'details',
    key: 'details',
  },
  {
    title: 'action_time',
    dataIndex: 'action_time',
    key: 'action_time',
  },
]);
const logs = async () => {
  try {
    dataSource.value = await $log({pageIndex: 1, pageSize: 10, name: ''}); // 更新响应式状态
  } catch (error) {
    console.error('加载数据失败:', error);
    // 错误处理逻辑
  }
};
logs();
</script>

<style>
/* 添加一些样式使表格更美观 */
table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
}

th {
  background-color: #f2f2f2;
}
</style>
