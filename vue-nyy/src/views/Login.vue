<style scoped>
.outer-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
}

.form-container {
  width: 100%;
  max-width: 500px;
  padding: 100px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-color: #ffffff;

}



</style>
<template>
  <link rel="icon" type="image/svg" href="../icons/favicon/favicon.svg">
  <div class="outer-container">
    <div class="form-container">
      <a-form
          ref="formRef"
          name="basic"
          :model="formState"
          v-bind="layout"
          labelAlign='right'
          autocomplete="off"

          @finish="onFinish"
          @finishFailed="onFinishFailed"
          id="login-form"
          class="custom-form"

      >
        <!-- 登录表单项 -->
        <a-form-item label="邮箱" name="email" :rules="[{ required: true, message: '请输入邮箱!' }]">
          <a-input v-model:value="formState.email" class="custom-input" />
        </a-form-item>

        <a-form-item label="密码" name="passwordHash" :rules="[{ required: true, message: '请输入密码!' }]">
          <a-input-password v-model:value="formState.passwordHash" class="custom-input"/>
        </a-form-item>



        <!-- 注册表单项，初始状态隐藏 -->
        <a-form-item v-if="showRegisterForm" label="用户姓名" name="username" :rules="[{ required: true, message: '请输入用户名称!' }]">
          <a-input v-model:value="formState.username" class="custom-input"/>
        </a-form-item>

        <a-form-item v-if="showRegisterForm" label="手机号" name="phoneNumber" :rules="[{ required: true, message: '请输入手机号!' }]">
          <a-input v-model:value="formState.phoneNumber" class="custom-input"/>
        </a-form-item>

        <!-- 按钮行 -->
        <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
          <div class="button-container" >

            <a-button v-if="!showRegisterForm" type="primary" html-type="button" @click="onLogin" class="custom-button">
              确定
            </a-button>
            <a-button v-if="showRegisterForm" type="primary" html-type="button" @click="submitRegistration" class="custom-button">
              提交
            </a-button>
            <a-button style="margin-left: 10px" v-if="showRegisterForm" type="default" html-type="button" @click="cancelRegistration" class="custom-button">
              取消
            </a-button>
            <a-button style="margin-left: 10px" v-if="!showRegisterForm" type="default" html-type="button" @click="toRegister" class="custom-button">
              注册
            </a-button>

          </div>
        </a-form-item>
      </a-form>
    </div>
  </div>
</template>

<script setup>
//导入路由器
import  {useRouter} from "vue-router";
let $router = useRouter();
import { reactive, ref } from 'vue';
import { message } from 'ant-design-vue'; // 导入 message 对象
import {$login, $register} from "@/api/login.js";

const formRef = ref();
const layout = {
  labelCol: {
    span: 8,
  },
  wrapperCol: {
    span: 14,
  },
};

const formState = reactive({
  email: '',
  passwordHash: '',
  remember: false,
  username: '', // 新增的表单项
  phoneNumber: '', // 新增的表单项
});

const showRegisterForm = ref(false);

const onLogin = () => {
  // 手动触发表单验证和提交
  document.getElementById('login-form').dispatchEvent(new Event('submit', { cancelable: true, bubbles: true }));
};



const onFinishFailed = (errorInfo) => {
  console.log('Failed:', errorInfo);
};

const toRegister = () => {
  showRegisterForm.value = true;
};

const cancelRegistration = () => {
  showRegisterForm.value = false;
  resetForm(); // 取消注册时重置表单
};

const onFinish =  async (values) => {
  let  { email, passwordHash } = values;
  let ret = await $login({email, passwordHash});
  if(ret){
    //跳转到布局页
    await $router.push("/layout");
  }
};


const submitRegistration =  () => {
  let { username, email, passwordHash, phoneNumber } = formState;
  $register({ username, email, passwordHash, phoneNumber });
  // console.log("email:", email);
  // console.log("passwordHash:", passwordHash);
  // console.log("username:", username);
  // console.log("phoneNumber:", phoneNumber);
};

const resetForm = () => {
  // 重置表单数据
  formState.username = '';
  formState.phoneNumber = '';
  formState.remember = false;
};

</script>



