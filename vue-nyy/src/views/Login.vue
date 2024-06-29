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
    <a-form-item
        label="Email"
        name="email"
        :rules="[{ required: true, message: 'Please input your email!' }]"
    >
      <a-input v-model:value="formState.email" />
    </a-form-item>

    <a-form-item
        label="passwordHash"
        name="passwordHash"
        :rules="[{ required: true, message: 'Please input your password!' }]"
    >
      <a-input-password v-model:value="formState.passwordHash" />
    </a-form-item>


    <a-form-item name="remember" :wrapper-col="{ offset: 8, span: 16 }">
      <a-checkbox v-model:checked="formState.remember">记住密码</a-checkbox>
    </a-form-item>

    <a-row type="flex" justify="end">
      <a-col :span="8">
        <a-form-item>
          <a-button type="primary" html-type="submit">确定</a-button>
        </a-form-item>
      </a-col>

      <a-col :span="8">
        <a-form-item>
          <a-button type="primary" html-type="button" @click="toRegister" 注册 ></a-button>
        </a-form-item>
      </a-col>

    </a-row>

  </a-form>
</template>



<script setup>
import { reactive } from 'vue';
import axios from "axios";
const formState = reactive({
  email: '',
  passwordHash: '',
  remember: false,
});
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


const onFinishFailed = errorInfo => {
  console.log('Failed:', errorInfo);
};

const toRegister = () => {
  console.log('toRegister');
}
</script>