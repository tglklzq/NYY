<style scoped>
.outer-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
}

.form-container {
  width: 80%;
  max-width: 500px;
  padding: 50px;
  border-radius: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-color: #ffffff;

}
.button-container{
  display: flex;
  justify-content: center;
  align-items: center;
}


</style>
<template>

  <div class="outer-container">
    <div class="form-container">
      <h1 style="text-align: center" >酒店客房管理系统</h1>
      <a-form ref="formRef" name="basic" :model="formState" labelAlign='left'
              autocomplete="off" @finish="onFinish" @finishFailed="onFinishFailed" id="login-form" class="custom-form">
        <!-- 登录表单项 -->
        <a-form-item label="用户邮箱" name="email">
          <a-input v-model:value="formState.emailValue1" class="custom-input" placeholder="请输入邮箱账号" allow-clear>
            <template #prefix><MailOutlined style="color: rgba(0, 0, 0, 0.25)" /></template>
            <template #addonAfter>
              <a-select v-model:value="formState.emailValue2" style="width: 120px">
                <a-select-option value="@qq.com">@qq.com</a-select-option>
                <a-select-option value="@163.com">@163.com</a-select-option>
                <a-select-option value="@164.com">@164.com</a-select-option>
                <a-select-option value="@165.com">@165.com</a-select-option>
                <a-select-option value="@166.com">@166.com</a-select-option>
              </a-select>
            </template>
          </a-input>
        </a-form-item>

        <a-form-item label="用户密码" name="passwordHash" >
          <a-input-password v-model:value="formState.passwordHash" class="custom-input" placeholder="请输入密码" allow-clear>
            <template #prefix><LockOutlined style="color: rgba(0, 0, 0, 0.25)" /></template>
          </a-input-password>
        </a-form-item>

        <!-- 注册表单项，初始状态隐藏 -->
        <a-form-item v-if="showRegisterForm" label="用户姓名" name="username" >
          <a-input v-model:value="formState.username" class="custom-input" placeholder="请输入名称" allow-clear>
            <template #prefix><UserOutlined style="color: rgba(0, 0, 0, 0.25)" /></template>
          </a-input>
        </a-form-item>

        <a-form-item v-if="showRegisterForm" label="手机号码" name="phoneNumber" >
          <a-input v-model:value="formState.phoneNumber" class="custom-input" placeholder="请输入手机号码" allow-clear>
            <template #prefix><PhoneOutlined style="color: rgba(0, 0, 0, 0.25)" /></template>
          </a-input>
        </a-form-item>

        <!-- 按钮行 -->
        <a-form-item >
          <div class="button-container" >

            <a-button v-if="!showRegisterForm" type="primary" html-type="button" @click="onFinish" class="custom-button">
              确定
            </a-button>
            <a-button v-if="showRegisterForm" type="primary" html-type="button" @click="submitRegistration" class="custom-button">
              提交
            </a-button>
            <a-button style="margin-left: 100px" v-if="showRegisterForm" type="default" html-type="button" @click="cancelRegistration" class="custom-button">
              取消
            </a-button>
            <a-button style="margin-left: 100px" v-if="!showRegisterForm" type="default" html-type="button" @click="toRegister" class="custom-button">
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
import {computed, reactive, ref} from 'vue';
import {$login, $register} from "@/api/login.js";
import { LockOutlined, MailOutlined,
  PhoneOutlined, UserOutlined } from '@ant-design/icons-vue';

const formRef = ref();
const showRegisterForm = ref(false);
const formState = reactive({
  emailValue1: '',
  emailValue2: '@qq.com',
  email: '',  // 添加 email 字段
  passwordHash: '',
  remember: false,
  username: '', // 新增的表单项
  phoneNumber: '', // 新增的表单项
});

const concatenatedEmail = computed(() => `${formState.emailValue1}${formState.emailValue2}`);

//重置表单
const resetForm = () => {
  // 重置表单数据
  formState.username = '';
  formState.phoneNumber = '';
  formState.remember = false;
};
const onFinish =  async () => {
  let email = concatenatedEmail.value;
  let passwordHash = formState.passwordHash;
  if (email && passwordHash){
    //console.log('登录:', email, passwordHash);
    let ret = await $login({email, passwordHash});
    if(ret){
      //跳转到布局页
      await $router.push("/layout");
    }
  }
  else{
    //console.log('登录:', email, passwordHash);
    alert("请完整输入信息");
  }
};
const onFinishFailed = (errorInfo) => {
  console.log('Failed:', errorInfo);
};
const toRegister = () => {
  showRegisterForm.value = true;
};

const submitRegistration =  () => {
  let email = concatenatedEmail.value;
  let { username, passwordHash, phoneNumber } = formState;
  if(!email || !passwordHash || !username || !phoneNumber){
    alert("请完整输入信息");
  }
  else {
    let res =$register({ username, email, passwordHash, phoneNumber });
    if(res){
      //alert("注册成功");
      showRegisterForm.value = false;
      resetForm();
    }
  }
  // console.log("email:", email);
  // console.log("passwordHash:", passwordHash);
  // console.log("username:", username);
  // console.log("phoneNumber:", phoneNumber);
};

const cancelRegistration = () => {
  showRegisterForm.value = false;
  resetForm(); // 取消注册时重置表单
};
</script>



