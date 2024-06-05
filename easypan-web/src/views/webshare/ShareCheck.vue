<template>
  <div class="share">
    <div class="body-content">
      <div class="logo">
        <span class="iconfont icon-pan"></span>
        <span class="name">魔女云盘</span>
      </div>

      <div class="code-panel">
        <div class="file-info">
          <div class="avatar">
            <Avatar
              :userId="shareInfo.userId"
              :avatar="shareInfo.avatar"
              :width="50"
            ></Avatar>
          </div>
          <div class="share-info">
            <div class="user-info">
              <span class="nick-name">{{ shareInfo.nickName }} </span>
              <span class="share-time">分享于 {{ shareInfo.shareTime }}</span>
            </div>
            <div class="file-name">分享文件：{{ shareInfo.fileName }}</div>
          </div>
        </div>

        <div class="code-body">
          <div class="tips">请输入提取码：</div>
          <div class="input-area">
            <el-form
              :model="formData"
              :rules="rules"
              ref="formDataRef"
              :maxLength="5"
              @submit.prevent
            >
              <el-form-item prop="code">
                <el-input
                  class="input"
                  v-model="formData.code"
                  @keyup.enter="checkShare"
                ></el-input>
                <el-button type="primary" @click="checkShare"
                  >提取文件</el-button
                >
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, getCurrentInstance, nextTick } from "vue";
import { useRouter, useRoute } from "vue-router";
import axios from "axios";
import {ElMessage} from "element-plus";
const { proxy } = getCurrentInstance();
const router = useRouter();
const route = useRoute();

const api = {
  getShareInfo: "/api/showShare/getShareInfo",
  checkShareCode: "/api/showShare/checkShareCode",
};

const shareId = route.params.shareId;
const shareInfo = ref({});
// 获取数据
const getShareInfo = async () => {
  try {
    const response = await axios.get(api.getShareInfo, {
      params: {
        shareId,
      },
    });
    if (response.data.message !== "分享文件已失效或不存在") {
      shareInfo.value = response.data;
    } else {
      ElMessage({
        type: 'error',
        message: "分享文件已失效或不存在"
      });
    }
  } catch (error) {
    ElMessage({
      type: 'error',
      message: error
    });
  }
};
getShareInfo();

const formData = ref({});
const formDataRef = ref();
const rules = {
  code: [
    { required: true, message: "请输入提取码" },
    { min: 5, message: "提取码为5位" },
    { max: 5, message: "提取码为5位" },
  ],
};

const checkShare = async () => {
  try {
    // 进行表单验证
    await formDataRef.value.validate();

    // 发送 Axios 请求
    const response = await axios.post(api.checkShareCode, {
      shareId: shareId,
      code: formData.value.code
    });

    // 检查返回结果
    if (response.data === "success") {
      router.push(`/share/${shareId}`);
    } else {
      ElMessage({
        type: 'error',
        message: response.data.message
      });
    }
  } catch (error) {
    console.log(error)
  }
};
</script>

<style lang="scss" scoped>
.share {
  height: calc(100vh);
  background: url("../../assets/share_bg.png");
  background-repeat: repeat-x;
  background-position: 0 bottom;
  background-color: #eef2f6;
  display: flex;
  justify-content: center;
  .body-content {
    margin-top: calc(100vh / 5);
    width: 500px;
    .logo {
      display: flex;
      align-items: center;
      justify-content: center;
      .icon-pan {
        font-size: 60px;
        color: #409eff;
      }
      .name {
        font-weight: bold;
        margin-left: 5px;
        font-size: 25px;
        color: #409eff;
      }
    }
    .code-panel {
      margin-top: 20px;
      background: #fff;
      border-radius: 5px;
      overflow: hidden;
      box-shadow: 0 0 7px 1px #5757574f;
      .file-info {
        padding: 10px 20px;
        background: #409eff;
        color: #fff;
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
      .code-body {
        padding: 30px 20px 60px 20px;
        .tips {
          font-weight: bold;
        }
        .input-area {
          margin-top: 10px;
          .input {
            flex: 1;
            margin-right: 10px;
          }
        }
      }
    }
  }
}
</style>
