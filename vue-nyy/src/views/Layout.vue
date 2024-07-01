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
      <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
        <a-menu-item key="1">
          <user-outlined />
          <span>nav 1</span>
        </a-menu-item>
        <a-menu-item key="2">
          <video-camera-outlined />
          <span>nav 2</span>
        </a-menu-item>
        <a-menu-item key="3">
          <upload-outlined />
          <span>nav 3</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0">
        <menu-unfold-outlined
            v-if="collapsed"
            class="trigger"
            @click="() => (collapsed = !collapsed)"
        />
        <menu-fold-outlined v-else class="trigger" @click="() => (collapsed = !collapsed)" />
      </a-layout-header>
      <!--主体-->
      <a-layout-content
          :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }"
      >
        Content
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script>
import { defineComponent, ref } from 'vue';
import { UserOutlined, VideoCameraOutlined, UploadOutlined, MenuUnfoldOutlined, MenuFoldOutlined } from '@ant-design/icons-vue';

export default defineComponent({
  components: {
    UserOutlined,
    VideoCameraOutlined,
    UploadOutlined,
    MenuUnfoldOutlined,
    MenuFoldOutlined,
  },
  setup() {
    return {
      selectedKeys: ref(['1']),
      collapsed: ref(false),
    };
  },
});
</script>

<style scoped lang="scss">
.layout {
  width: 100vw;
  height: 100vh;
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
</style>
