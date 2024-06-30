<template>
  <a-form
      :model="formState"
      name="basic"
      :label-col="{ span: 8 }"
      :wrapper-col="{ span: 16 }"
      autocomplete="off"
      @finish="onFinish"
      @finishFailed="onFinishFailed"
      @register="toRegister"
  >
    <!-- 原始表单项 -->
    <a-form-item
        label="邮箱"
        name="email"
        :rules="[{ required: true, message: '请输入邮箱!' }]"
    >
      <a-input v-model:value="formState.email" />
    </a-form-item>

    <a-form-item
        label="密码"
        name="passwordHash"
        :rules="[{ required: true, message: '请输入密码!' }]"
    >
      <a-input-password v-model:value="formState.passwordHash" />
    </a-form-item>

    <!-- 新增的注册表单项，初始状态隐藏 -->
    <a-form-item
        v-if="showRegisterForm"
        label="用户名称"
        name="username"
        :rules="[{ required: true, message: '请输入用户名称!' }]"
    >
      <a-input v-model:value="formState.username" />
    </a-form-item>

    <a-form-item
        v-if="showRegisterForm"
        label="手机号"
        name="phoneNumber"
        :rules="[{ required: true, message: '请输入手机号!' }]"
    >
      <a-input v-model:value="formState.phoneNumber" />
    </a-form-item>

    <!-- 记住密码复选框 -->
    <a-form-item name="remember" :wrapper-col="{ offset: 8, span: 16 }">
      <a-checkbox v-model:checked="formState.remember">记住密码</a-checkbox>
    </a-form-item>

    <!-- 按钮行 -->
    <a-form-item>
      <a-row type="flex" justify="end">
        <a-col :span="8">
          <a-form-item>
            <a-button
                v-if="!showRegisterForm"
                type="primary"
                html-type="submit"
                @click="onFinish"
            >
              确定
            </a-button>
            <a-button
                v-if="showRegisterForm"
                type="primary"
                html-type="button"
                @click="submitRegistration"
            >
              提交
            </a-button>
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item>
            <a-button
                v-if="showRegisterForm"
                type="primary"
                html-type="button"
                @click="cancelRegistration"
            >
              取消
            </a-button>
          </a-form-item>
        </a-col>

        <a-col :span="8">
          <a-form-item>
            <a-button
                v-if="!showRegisterForm"
                type="primary"
                html-type="button"
                @click="toRegister"
            >
              注册
            </a-button>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form-item>
  </a-form>
</template>

<script setup>
import { reactive, ref } from 'vue';
import { message } from 'ant-design-vue';
import axios from 'axios';

const formState = reactive({
  email: '',
  passwordHash: '',
  remember: false,
  username: '', // 新增的表单项
  phoneNumber: '', // 新增的表单项
});

const showRegisterForm = ref(false);

const onFinish = async (values) => {
  const { email, passwordHash } = values;
  console.log('Email: ', email);
  console.log('Password: ', passwordHash);
  try {
    const response = await axios.post('/api/user/login', { email, passwordHash });

    // 打印出整个 response.data，确保其结构符合预期
    console.log('Response Data:', response.data);

    const { statusCode, success, message, data } = response.data;

    if (success) {
      console.log(message);
      // 在这里添加登录成功后的逻辑，例如跳转到主页等
    } else {
      console.error(message);
    }
  } catch (error) {
    // 网络请求失败或后端服务出现问题
    console.error('登录请求失败，请稍后再试。', error);
  }
};

const onFinishFailed = (errorInfo) => {
  console.log('Failed:', errorInfo);
};

const toRegister = () => {
  showRegisterForm.value = true;
};

const cancelRegistration = () => {
  showRegisterForm.value = false;
  formState.username = ''; // 清空用户名称
  formState.phoneNumber = ''; // 清空手机号
};

const submitRegistration = async (values) => {
  const { username, email, passwordHash, phoneNumber } = formState;
  console.log("email:", email);
  console.log("passwordHash:", passwordHash);
  console.log("username:", username);
  console.log("phoneNumber:", phoneNumber);

  try {
    const response = await axios.post('/api/user/register', {
      email,
      passwordHash,
      username,
      phoneNumber,
    });
    console.log('注册响应:', response.data);
    // 处理成功或显示消息
  } catch (error) {
    console.error('注册失败:', error);
  }
};

</script>

<style scoped>
/* 可以在这里添加你的样式 */
</style>
