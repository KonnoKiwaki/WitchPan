<template>
  <!-- 文件选择/文件移动 -->
  <div>
    <Dialog
      :show="dialogConfig.show"
      :title="dialogConfig.title"
      :buttons="dialogConfig.buttons"
      width="600px"
      :showCancel="true"
      @close="dialogConfig.show = false"
    >
      <!-- 目录导航 -->
      <div class="navigation-panel">
        <Navigation
          ref="navigationRef"
          @navChange="navChange"
          :watchPath="false"
        ></Navigation>
      </div>
      <div class="folder-list" v-if="folderList.length > 0">
        <div
          class="folder-item"
          v-for="item in folderList"
          @click="selectFolder(item)"
        >
          <Icon :fileType="0"></Icon>
          <span class="file-name">{{ item.filename }}</span>
        </div>
      </div>
      <div v-else style="text-align: center" class="tips">
        移动到 <span style="vertical-align: middle; padding: 3px">{{ currentFolder.filename }}</span> 文件夹
      </div>
    </Dialog>
  </div>
</template>

<script setup>
import {ref, reactive, getCurrentInstance, nextTick} from "vue";
import { useRouter, useRoute } from "vue-router";
import axios from "axios";
import {ElMessage} from "element-plus";
import {changeFileFolderApi, changeShareFolderApi} from "@/api/file";
import {isArray} from "@/utils/is";

const { proxy } = getCurrentInstance();
const router = useRouter();
const route = useRoute();

const api = {
  loadAllFolder: "/api/showShare/loadAllFolder",
};

// 定义弹出框的属性
const dialogConfig = ref({
  show: false,
  title: "移动到",
  buttons: [
    {
      type: "primary",
      text: "移动到此",
      click: (e) => {
        folderSelect();
      },
    },
  ],
});

// 目录列表
const folderList = ref([]);
// 父级ID
const filePid = ref("0");
// 当前目录ID
const currentFileIds = ref([]);
// 当前文件夹
const currentFolder = ref({});

// 获取所有的目录
const loadAllFolder = async () => {
  try {
    const response = await axios.get(api.loadAllFolder, {
      params: {
        filePid: filePid.value,
        // currentFileIds: currentFileIds.value,
      }
    });

    if (!response.data) {
      return;
    }
    folderList.value = response.data;
  } catch (error) {
    console.error('Error while loading folders:', error);
  }
};

// 展示弹出框对外的方法
const showFolderDialog = (currentFolder) => {
  dialogConfig.value.show = true;
  currentFileIds.value = currentFolder;
  loadAllFolder();
};

// 关闭弹出框
const close = () => {
  dialogConfig.value.show = false;
};
// 向外暴露这两个函数，使得父组件Main可以调用这两个函数
defineExpose({ showFolderDialog, close });

// 绑定导航栏
const navigationRef = ref();

// 调用Navigation子组件中的navChange,使得参数传递给该组件
const navChange = (data) => {
  const { curFolder } = data;
  currentFolder.value = curFolder;
  filePid.value = curFolder.id;
  loadAllFolder();
};

// 选择目录(目录导航)
const selectFolder = (data) => {
  navigationRef.value.openFolder(data);
};

let fileIds = [];


// 确定选择要移动到的目录
// 将选定的文件目录参数传递给父组件 Main 中的 folderSelect 函数
const emit = defineEmits(["folderSelect"]);

//TODO
const folderSelect = async () => {
  emit("folderSelect", filePid.value);
  dialogConfig.show = false
};
</script>

<style lang="scss" scoped>
.navigation-panel {
  padding-left: 10px;
  background: #f1f1f1;
}
.folder-list {
  .folder-item {
    cursor: pointer;
    display: flex;
    align-items: center;
    padding: 10px;
    .file-name {
      display: inline-block;
      margin-left: 10px;
    }
    &:hover {
      background: #f8f8f8;
    }
  }
  max-height: calc(100vh - 200px);
  min-height: 200px;
}
.tips {
  display: flex;
  align-items: center;
  text-align: center;
  line-height: 200px;
  justify-content: center;
  span {
    color: #06a7ff;
  }
}
</style>
