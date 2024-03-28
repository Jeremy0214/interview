<template>
  <main>
    <div>
      <a-layout>
        <a-layout-header class="header">
          <a-dropdown>
            <div style="float: right">
              <img
                src="@/assets/user1.png"
                style="width: 50px"
                class="ant-dropdown-link"
                @click.prevent
              />
              <span style="color: #fff">{{ this.store.getUserName() }}登入成功</span>
            </div>
            <template #overlay>
              <a-menu>
                <a-menu-item> <a @click="gologout()">登出</a> </a-menu-item>
              </a-menu>
            </template>
          </a-dropdown>
          <a-menu theme="dark" mode="horizontal" :style="{ lineHeight: '64px' }">
            <a-menu-item key="1">Home</a-menu-item>
          </a-menu>
        </a-layout-header>
        <a-layout-content style="padding: 0 50px">
          <a-breadcrumb style="margin: 16px 0">
            <a-breadcrumb-item>Home</a-breadcrumb-item>
          </a-breadcrumb>
          <a-layout style="padding: 24px 0; background: #fff">
            <a-layout-sider width="200" style="background: #fff">
              <a-menu mode="inline" style="height: 100%">
                <template #title>
                  <span> subnav 1 </span>
                </template>
                <a-menu-item v-for="link in routerLinks" :key="link.key">
                  <RouterLink :to="link.to">{{ link.label }}</RouterLink>
                </a-menu-item>
              </a-menu>
            </a-layout-sider>
            <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
              <div>
                <router-view></router-view>
              </div>
            </a-layout-content>
          </a-layout>
        </a-layout-content>
        <a-layout-footer style="text-align: center">
          Jeremy's Design ©2024 Created by Jeremy
        </a-layout-footer>
      </a-layout>
    </div>
  </main>
</template>
<script>
import { useAuthStore } from '../stores/auth' //引用store
export default {
  data() {
    return {
      store: useAuthStore()
    }
  },
  methods: {
    gologout() {
      this.store.logout().then(this.$router.push('/login'))
    }
  },
  mounted() {},
  computed: {
    routerLinks() {
      return [
        { key: 'test1', to: '/test1', label: 'test1' },
        { key: 'test2', to: '/test2', label: 'test2' },
        { key: 'test3', to: '/test3', label: 'test3' },
        { key: 'test4', to: '/test4', label: 'test4' },
        { key: 'test5', to: '/test5', label: 'test5' },
        { key: 'ProductView', to: '/ProductView', label: 'ProductView' },
        { key: 'Practice', to: '/Practice', label: 'Practice' },
      ]
    }
  }
}
</script>
<style scoped></style>
