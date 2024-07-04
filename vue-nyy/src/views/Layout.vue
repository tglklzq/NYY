<template>
  <a-layout class="layout">
    <!-- 左侧菜单栏 -->
    <a-layout-sider v-model:collapsed="collapsed" :trigger="null" collapsible>
      <!-- logo -->
      <div class="logo-container">
        <template v-if="!collapsed">
          <div class="logo-text">酒店客房管理系统</div>
        </template>
        <template v-else>
          <img class="logo-icon" src="@/icons/svg/plant.svg" alt="Icon">
        </template>
      </div>

      <!-- 菜单 -->
      <a-menu v-model:selectedKeys="selectedKeys" mode="inline" theme="light">
        <a-sub-menu v-for="(item1, index1) in mineMenus" :key="index1">
          <template #icon>
            <img :src="getMenuIcon(item1.title)" alt="icon" class="menu-icon" />
          </template>
          <template #title>{{ item1.title }}</template>
          <a-menu-item v-for="(item2, index2) in item1.children" :key="`${item1.title}-${index2}`">{{ item2.title }}</a-menu-item>
        </a-sub-menu>
      </a-menu>

    </a-layout-sider>
    <!-- 顶部 -->
    <a-layout>
      <a-layout-header class="header">
        <menu-unfold-outlined v-if="collapsed" class="trigger" @click="() => (collapsed = !collapsed)" />
        <menu-fold-outlined v-else class="trigger" @click="() => (collapsed = !collapsed)" />
        <a-menu v-model:selectedKeys="currentKeys" mode="horizontal" theme="dark">
          <a-menu-item key="mail">
            <template #icon><mail-outlined /></template>邮件
          </a-menu-item>
          <a-menu-item key="message">
            <template #icon><bell-outlined /></template>消息
          </a-menu-item>
          <a-sub-menu key="admin">
            <template #icon><user-outlined /></template>
            <template #title>管理员</template>
            <a-menu-item key="setting:1">个人中心</a-menu-item>
            <a-menu-item key="setting:2">修改密码</a-menu-item>
            <a-menu-item key="setting:3" @click="exit()">退出登录</a-menu-item>
          </a-sub-menu>
        </a-menu>
      </a-layout-header>
      <!-- 主体 -->
      <a-layout-content :style="{ margin: '6px', padding: '10px', background: '#fff', minHeight: '280px' }">
        <router-view />
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script>
import {defineComponent, ref, watchEffect} from 'vue';
import {useRouter} from 'vue-router';
import {BellOutlined, MailOutlined, MenuFoldOutlined, MenuUnfoldOutlined, UserOutlined} from '@ant-design/icons-vue';
import SvgIcon from '@/components/SvgIcon/index.vue';
import router from '@/router/index.js';

export default defineComponent({
  components: {
    SvgIcon,
    UserOutlined,
    BellOutlined,
    MailOutlined,
    MenuUnfoldOutlined,
    MenuFoldOutlined,
  },
  setup() {
    const $router = useRouter();
    const collapsed = ref(false);
    const selectedKeys = ref(["1"]);
    const currentKeys = ref(['']);
    const mineMenus = ref([]);

    // 退出系统
    const exit = () => {
      sessionStorage.clear();
      localStorage.clear();
    //  console.log('退出系统');
      $router.replace('/');
    };

    // 动态获取菜单图标的路径
    const getMenuIcon = (title) => {
      return new URL(`../icons/svg/${title}.svg`, import.meta.url).href;
    };

    // Watch sessionStorage changes
    watchEffect(() => {
      mineMenus.value = JSON.parse(sessionStorage.getItem('mineMenus')) || [];
    //  console.log('mineMenus updated:', mineMenus.value);
    });

    return {
      collapsed,
      currentKeys,
      selectedKeys,
      mineMenus,
      getMenuIcon,
      exit,
    };
  },
});
</script>

<style scoped lang="scss">
.layout {
  width: 100vw;
  height: 100vh;

  .header {
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

.trigger {
  color: white;
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;

  &:hover {
    color: #1890ff;
  }
}

.menu-icon {
  width: 20px; /* 调整图标的宽度 */
  height: 20px; /* 调整图标的高度 */
}
</style>
