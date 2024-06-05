<template>
  <div class="main-setting">
    <el-form
      :model="formData"
      :rules="rules"
      ref="formDataRef"
      label-width="100px"
      @submit.prevent
    >
      <el-form-item label="注册邮箱标题" props="registerEmailTitle">
        <el-input
          clearable
          placeholder="注册邮箱验证码邮件标题"
          v-model.trim="formData.registerEmailTitle"
        />
      </el-form-item>
      <el-form-item label="注册邮箱内容" props="registerEmailContent">
        <el-input
          clearable
          placeholder="注册邮箱验证码邮件内容"
          v-model.trim="formData.registerEmailContent"
        />
      </el-form-item>
      <el-form-item label="初始空间大小" props="useInitUserSpace">
        <el-input
          clearable
          placeholder="初始空间大小"
          v-model.trim="formData.userInitSpace"
        >
        <template #suffix>GB</template>
      </el-input>
    </el-form-item>
    <el-form-item style="float: right;">
      <el-button type="primary" @click="saveSettings">保存</el-button>
    </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'SystemSetting'
}
</script>
<script setup>
import Verify from '@/utils/Verify';
import {onMounted, ref} from 'vue';
import axios from "axios";
import {ElMessage} from "element-plus";

let rules = {
  registerEmailTitle: { required: true, message: '请输入注册邮箱验证码邮件标题' },
  registerEmailContent: { required: true, message: '请输入注册邮箱验证码邮件内容' },
  userInitSpace: [{ required: true, message: '请输入初始空间大小' },
  { validator: Verify.number, message: '空间大小只能是数字' }]
}

let formData = ref({})
const formDataRef = ref()

const loadingSettings = () => {
  axios.get('/api/admin/getSysSettings')
      .then(response => {
        if(response.data.code === 2005) {
          ElMessage({
            showClose: true,
            message: response.data.message,
            type: 'error'
          });
        } else {
          formData.value = response.data;
        }
      })
      .catch(error => {
        ElMessage({
          showClose: true,
          message: error,
          type: 'error'
        });
      });
}
const saveSettings = () => {
  axios.post("/api/admin/saveSysSettings", formData.value)
      .then(response => {
        if(response.data.code === 2005) {
          ElMessage({
            showClose: true,
            message: "修改失败",
            type: 'error'
          });
        } else {
          ElMessage({
            type: 'success',
            message: '修改成功',
          })
        }
      })
      .catch(error => {
        ElMessage({
          showClose: true,
          message: error,
          type: 'error'
        });
      });
}
loadingSettings();



</script>
<style lang="scss" scoped>
.main-setting {
  padding: 20px 24px;
  width: 600px;
}

</style>
