<template>
  <div>
    <a-descriptions title="User Info" bordered>
        <a-descriptions-item label="账号ID">{{ userInfo.admin_id }}</a-descriptions-item>
        <a-descriptions-item label="用户名称">{{ userInfo.username }}</a-descriptions-item>
        <a-descriptions-item label="用户邮箱">{{ userInfo.email }}</a-descriptions-item>
        <a-descriptions-item label="手机号码">{{ userInfo.phone_number }}</a-descriptions-item>
        <a-descriptions-item label="权限内容">{{ userInfo.permission_name }}</a-descriptions-item>
        <a-descriptions-item label="权限名称">{{ userInfo.role_name }}</a-descriptions-item>
        <a-descriptions-item label="创建时间">{{ userInfo.createdAt }}</a-descriptions-item>
        <a-descriptions-item label="更新时间">{{ userInfo.updatedAt }}</a-descriptions-item>
        <a-descriptions-item label="注销时间">{{ userInfo.deletedAt }}</a-descriptions-item>
        <a-descriptions-item label="账号状态">{{ userInfo.isDeleted }}</a-descriptions-item>
    </a-descriptions>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue';
import {$personalInformation} from "@/api/adminsTable.js";
import {message} from 'ant-design-vue';
const userInfo = ref({});

const fetchUserInfo = async () => {
  const user = JSON.parse(sessionStorage.getItem('user'));
  if (user) {
    try {
      userInfo.value = await $personalInformation({email: user.email});
    } catch (error) {
      message.error(error.message);
    }
  }
};
onMounted(fetchUserInfo);
</script>

<style scoped>
</style>
