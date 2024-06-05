<template>
  <div class="share">
    <div class="header">
      <div class="header-content">
        <div class="logo" @click="jump">
          <span class="iconfont icon-pan"></span>
          <span class="name">魔女云盘</span>
        </div>
      </div>
    </div>

    <div class="share-body">
      <template v-if="Object.keys(shareInfo).length == 0">
        <div v-loading="Object.keys(shareInfo).length == 0" class="loading"></div>
      </template>
      <template v-else>
        <div class="share-panel">
          <div class="share-user-info">
            <div class="avatar">
              <Avatar :userId="shareInfo.userId" :avatar="shareInfo.avatar" :width="50"></Avatar>
            </div>
            <div class="share-info">
              <div class="user-info">
                <span class="nick-name">{{ shareInfo.nickName }} </span>
                <span class="share-time">分享于 {{ shareInfo.shareTime }}</span>
              </div>
              <div class="file-name">分享文件：{{ shareInfo.fileName }}</div>
            </div>
          </div>

          <div class="share-op-btn">
            <el-button type="primary" v-if="shareInfo.currentUser" @click="cancelShare">
              <span class="iconfont icon-cancel"></span>取消分享
            </el-button>
            <el-button type="primary" v-else :disabled="selectFileIdList.length == 0" @click="save2MyPan">
              <span class="iconfont icon-import"></span>保存到我的网盘
            </el-button>
          </div>
        </div>
        <ShareNavigation ref="navigationRef" @navChange="navChange" :shareId="shareId"></ShareNavigation>
        <div class="file-list">
          <Table
              :columns="columns"
              :showPagination="true"
              :dataSource="tableData"
              :fetch="loadDataList"
              :initFetch="false"
              :options="tableOptions"
              @rowSelected="rowSelected"
          >
            <template #fileName="{ index, row }">
              <div class="file-item" @mouseenter="showOp(row)" @mouseleave="cancelShowOp(row)">
                <template v-if="(row.fileType == 3 || row.fileType == 1) && row.status !== 0">
                  <Icon :cover="row.fileCover"></Icon>
                </template>
                <template v-else>
                  <Icon v-if="row.folderType == 0" :fileType="row.fileType"></Icon>
                  <Icon v-if="row.folderType == 1" :fileType="0"></Icon>
                </template>
                <span class="file-name" :title="row.filename">
                  <span class="text" @click.stop="preview(row)">{{ row.filename }}</span>
                </span>
                <span class="op">
                  <span  style="cursor: pointer" class="iconfont icon-download" @click="download(row.id)">下载</span>
                  <template v-if="row.showOp && !shareInfo.currentUser">
<!--                    <span class="iconfont icon-import" style="cursor: pointer" @click="save2MyPanSingle(row)">保存到我的网盘</span>-->
                  </template>
                </span>
              </div>
            </template>
            <template #fileSize="{ index, row }">
              <span v-if="row.fileSize">{{ proxy.Utils.size2Str(row.fileSize) }}</span>
            </template>
          </Table>
        </div>
      </template>
      <FolderSelect ref="folderSelectRef" @folderSelect="save2MyPanDone"></FolderSelect>
      <Preview ref="previewRef"></Preview>
    </div>
  </div>
</template>

<script setup>
import {ref, reactive, getCurrentInstance, inject, onMounted} from "vue";
import { useRouter, useRoute } from "vue-router";
import axios from "axios";
const { proxy } = getCurrentInstance();
const router = useRouter();
const route = useRoute();
import Table from "@/components/Table.vue";
import { ElMessage } from "element-plus";
import Navigation from "@/components/Navigation.vue";
import Preview from "@/components/preview/Preview.vue";
import ShareNavigation from "@/components/Table/ShareNavigation.vue";
import FolderSelect from "@/components/FolderSelect.vue";

const api = {
  getShareLoginInfo: "/api/showShare/getShareLoginInfo",
  loadFileList: "/api/showShare/loadFileList",
  createDownloadUrl: "/api/showShare/createDownloadUrl",
  download: "/api/showShare/download",
  cancelShare: "/api/showShare/cancelShare",
  saveShare: "/api/showShare/saveShare",
};

let shareId = route.params.shareId;
const shareInfo = ref({});

const getShareInfo = async () => {
  try {
    let response = await axios.get(api.getShareLoginInfo, { params: { shareId: shareId } });
    if (response.data.message === "系统错误") {
      ElMessage({
        type: "error",
        message: "系统错误,请重新验证",
      });
      router.push("/shareCheck/" + shareId);
      return;
    }
    shareInfo.value = response.data;
  } catch (error) {
    ElMessage({
      type: "error",
      message: error,
    });
  }
};
getShareInfo();

const columns = [
  { label: "文件名", prop: "filename", scopedSlots: "fileName", align: "left" },
  { label: "修改时间", prop: "updateTime", width: 200, align: "center" },
  { label: "大小", prop: "fileSize", scopedSlots: "fileSize", width: 200, align: "center" },
];

const tableData = ref([]); // 初始化为数组
const tableOptions = { extHeight: 80, selectType: "checkbox" };
let currentFolder = ref({ id: "0" });

const loadDataList = async () => {
  try {
    let params = {
      page: tableData.value.pageNo || 1,
      limit: tableData.value.pageSize || 10,
      shareId: shareId,
      filePid: currentFolder.value.id,
    };
    let response = await axios.get(api.loadFileList, { params });
    if (!response.data) {
      return;
    }
    tableData.value = response.data; // 直接赋值为后端返回的列表数据
  } catch (error) {
    console.error(error);
  }
};

const showOp = (row) => {
  tableData.value.forEach((element) => {
    element.showOp = false;
  });
  row.showOp = true;
};

const cancelShowOp = (row) => {
  row.showOp = false;
};


const selectFileIdList = ref([]);
const rowSelected = (rows) => {
  selectFileIdList.value = [];
  rows.forEach((item) => {
    selectFileIdList.value.push(item.id);
  });
  //TODO 可以改
  // rows.forEach(item => {
  //
  // })
};
const navChange = (data) => {
  const { curFolder } = data;
  currentFolder.value = curFolder || { id: "0" };
  loadDataList();
};
loadDataList();

const navigationRef = ref();
const previewRef = ref();
const preview = (data) => {
  if (data.folderType == 1) {
    navigationRef.value.openFolder(data, shareId);
    return;
  }
  data.shareId = shareId;
  previewRef.value.showPreview(data, 2);
};

const download = async (fileId) => {
  try {
    const result = await axios.get(`${api.createDownloadUrl}/${shareId}/${fileId}`);
    if (!result.data) {
      return;
    }
    window.location.href = `${api.download}/${result.data.code}`;
  } catch (error) {
    console.error(error);
  }
};

const folderSelectRef = ref();
let save2MyPanFileIdArray = [];
let folderIds = ref();


//批量保存 进行选择
const save2MyPan = () => {
  if (selectFileIdList.value.length == 0) {
    return;
  }
  if (!proxy.VueCookies.get("userInfo")) {
    router.push("/login?redirectUrl=" + route.path);
    return;
  }
  //save2MyPanFileIdArray.values = selectFileIdList.value;
  folderSelectRef.value.showFolderDialog();
};

const save2MyPanSingle = (row) => {
  if (!proxy.VueCookies.get("userInfo")) {
    router.push("/login?redirectUrl=" + route.path);
    return;
  }
  //save2MyPanFileIdArray.values = [row.fileId];
  folderSelectRef.value.showFolderDialog();
};

let currentFolderId = ref();

//选择后的保存
const save2MyPanDone = async (folderId) => {
  // 确保接收到的 folderId 赋值给 folderIds
  try {
    console.log(route.params.shareId + "666" + selectFileIdList.value + "777" + folderId)
    const result = await axios.get(api.saveShare, {
      params: {
        shareId: route.params.shareId,
        shareFileIds: selectFileIdList.value.join(','),
        myFolderId: folderId,
      },
    });
    if (result.data.message === "不能保存自己的分享") {
      ElMessage.success('不能保存自己的分享');
    } else {
      ElMessage.success('保存成功');
    }
    loadDataList();
    folderSelectRef.value.close();
  } catch (error) {
    console.error(error);
  }
};

const cancelShare = () => {
  proxy.Confirm("你确定要取消分享吗？", async () => {
    try {
      const response = await axios.post(api.cancelShare, {
        shareIds: shareId
      });
      if (response.status === 200) {
        proxy.Message.success("取消分享成功");
        router.push("/");
      }
    } catch (error) {
      proxy.Message.error("操作失败");
    }
  });
};

const jump = () => {
  router.push("/");
};
</script>

<style lang="scss" scoped>
@import "@/assets/filelist.scss";
.header {
  width: 100%;
  position: fixed;
  background: #0c95f7;
  height: 50px;
  .header-content {
    width: 70%;
    margin: 0px auto;
    color: #fff;
    line-height: 50px;
    .logo {
      display: flex;
      align-items: center;
      cursor: pointer;
      .icon-pan {
        font-size: 40px;
      }
      .name {
        font-weight: bold;
        margin-left: 5px;
        font-size: 25px;
      }
    }
  }
}
.share-body {
  width: 70%;
  margin: 0px auto;
  padding-top: 50px;
  .loading {
    height: calc(100vh / 2);
    width: 100%;
  }
  .share-panel {
    margin-top: 20px;
    display: flex;
    justify-content: space-around;
    border-bottom: 1px solid #ddd;
    padding-bottom: 10px;
    .share-user-info {
      flex: 1;
      display: flex;
      align-items: center;
      .avatar {
        margin-right: 5px;
      }
      .share-info {
        .user-info {
          display: flex;
          align-items: center;
          .nick-name {
            font-size: 15px;
          }
          .share-time {
            margin-left: 20px;
            font-size: 12px;
          }
        }
        .file-name {
          margin-top: 10px;
          font-size: 12px;
        }
      }
    }
  }
}

.file-list {
  margin-top: 10px;
  .file-item {
    .op {
      width: 170px;
    }
  }
}
</style>
