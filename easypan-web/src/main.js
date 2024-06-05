import './assets/main.css'

import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { setupStore } from './store'
import App from './App.vue'
import router, { setupRouter } from './router'
import VueCookies from 'vue-cookies'

import Verify from './utils/Verify'
import Message from './utils/Message'
import Request from './utils/Request'
import Confirm from './utils/Confirm'
import Utils from './utils/Utils'

import Dialog from "@/components/Dialog.vue"
import Avatar from "@/components/Avatar.vue"
import Table from "@/components/Table.vue"
import Icon from "@/components/Icon.vue"
import NoData from "@/components/NoData.vue"
import FolderSelect from "@/components/FolderSelect.vue"
import Navigation from "@/components/Navigation.vue"
import Preview from "@/components/preview/Preview.vue"
import Window from "@/components/Window.vue"

import HljsVuePlugin from '@highlightjs/vue-plugin'
import "highlight.js/styles/atom-one-light.css";
import 'highlight.js/lib/common'


async function bootstrap() {
  const app = createApp(App)
  // 挂载状态管理
  setupStore(app)
  app.use(HljsVuePlugin)

  app.config.globalProperties.Verify = Verify;
  app.config.globalProperties.Message = Message;
  app.config.globalProperties.Request = Request;
  app.config.globalProperties.Confirm = Confirm;
  app.config.globalProperties.Utils = Utils;
  app.config.globalProperties.VueCookies = VueCookies;

  app.component('Table', Table)
  app.component('Dialog', Dialog)
  app.component('Avatar', Avatar)
  app.component('Icon', Icon)
  app.component('NoData', NoData)
  app.component('FolderSelect', FolderSelect)
  app.component('Navigation', Navigation)
  app.component('Preview', Preview)
  app.component('Window', Window)
  // 注册全局常用的 element-plus 组件
  app.use(ElementPlus)

  // 挂载路由
  setupRouter(app)

  // 路由准备就绪后挂载 APP 实例
  // https://router.vuejs.org/api/interfaces/router.html#isready
  await router.isReady()

  app.mount('#app', true)
}
bootstrap()
