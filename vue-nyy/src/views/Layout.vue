<template>
  <a-layout class="layout">
    <!--左侧菜单栏-->
    <a-layout-sider v-model:collapsed="collapsed" :trigger="null" collapsible>
      <!--logo-->
      <div class="logo-container">
        <template v-if="!collapsed">
          <div class="logo-text">酒店客房管理系统</div>
        </template>
        <template v-else>
          <img class="logo-icon" src="@/icons/svg/plant.svg" alt="Icon">
        </template>
      </div>

      <!--菜单-->

      <a-menu
          v-model:openKeys="openKeys"
          v-model:selectedKeys="selectedKeys"
          mode="inline"
          theme="dark"

      >
        <a-sub-menu key="sub1">
          <template #icon>
            <UserOutlined />
          </template>
          <template #title>Navigation One</template>
          <a-menu-item key="1">Option 5</a-menu-item>
          <a-menu-item key="2">Option 6</a-menu-item>
          <a-menu-item key="3">Option 7</a-menu-item>
          <a-menu-item key="4">Option 8</a-menu-item>
        </a-sub-menu>
      </a-menu>

    </a-layout-sider>
    <!--顶部-->
    <a-layout>
      <a-layout-header class="header" >
        <menu-unfold-outlined
            v-if="collapsed"
            class="trigger"
            @click="() => (collapsed = !collapsed)"
        />
        <menu-fold-outlined
            v-else class="trigger"
            @click="() => (collapsed = !collapsed)" />
        <a-menu v-model:selectedKeys="current" mode="horizontal" theme="dark">
          <a-menu-item key="mail">
            <template #icon>
              <mail-outlined />
            </template>
            邮件
          </a-menu-item>
          <a-menu-item key="message">
            <template #icon>
              <bell-outlined />
            </template>
            消息
          </a-menu-item>
          <a-sub-menu key="admin">
            <template #icon>
              <user-outlined />
            </template>
            <template #title>管理员</template>
              <a-menu-item key="setting:1">个人中心</a-menu-item>
              <a-menu-item key="setting:2">修改密码</a-menu-item>
              <a-menu-item key="setting:3">退出登录</a-menu-item>
          </a-sub-menu>

        </a-menu>
      </a-layout-header>
      <!--主体-->
      <a-layout-content
          :style="{ margin: '6px', padding: '10px', background: '#fff', minHeight: '280px' }"
      >
        Content
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script>
import { defineComponent, ref } from 'vue';
import { UserOutlined, VideoCameraOutlined, UploadOutlined,
  MenuUnfoldOutlined, MenuFoldOutlined,BellOutlined,MailOutlined } from '@ant-design/icons-vue';



export default defineComponent({
  components: {
    BellOutlined,MailOutlined,
    UserOutlined,
    VideoCameraOutlined,
    UploadOutlined,
    MenuUnfoldOutlined,
    MenuFoldOutlined,
  },
  setup() {
    return {
      //顶部菜单选中key
      current:ref(['mail']),
      //左侧菜单展开的选项
      openKeys:ref(['sub1']),
      selectedKeys: ref(['1']),
      //左侧菜单是否折叠
      collapsed: ref(false),
    };
  },
});
</script>

<style scoped lang="scss">
.layout {
  width: 100vw;
  height: 100vh;
  .header{
    background: #fff;
    padding: 0;
    display: flex;
    justify-content: space-between;
    background-color: #001529;
  }
  .logo-container {
    height: 64px;
    display: flex;
    align-items: center;
    justify-content: center;
    overflow: hidden;
    transition: width 0.2s;
  }
  .logo-text {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    color: #f0f2f5;
    text-align: center;
    line-height: 64px;
    font-size: 20px;
  }
  .logo-icon {
    width: 32px;
    height: 32px;
  }
}
.trigger{
  color: white;
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
  &:hover{
    color: #1890ff;
  }
}
</style>
