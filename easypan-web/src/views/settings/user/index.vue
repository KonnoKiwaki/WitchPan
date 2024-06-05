<template>
  <div class="main-user">
    <div class="main-header">
      <div class="actions">
        <div class="select">
          <el-select
            v-model="searchParam.select"
            class="status-select"
            placeholder="状态"
            :teleported="false"
            style="width: 80px"
          >
            <el-option  label="启用" value="1" />
            <el-option  label="禁用" value="0" />
          </el-select>
        </div>
        <div class="search">
          <el-input
            v-model.trim="searchParam.filename"
            size="small"
            clearable
            placeholder="搜索我的文件"
            input-style="height: 32px"
            @keyup.enter="searchFile"
            @clear="searchFile"
            class="input-with-select"
          >
            <template #suffix>
              <span class="text" @click="searchFile">搜索</span>
            </template>
          </el-input>
        </div>
      </div>
    </div>
    <div class="main-body">
      <div class="file-list">
        <BasicTable
          ref="userTableRef"
          :columns="canEditColumns"
          :row-key="(row) => row.id"
          :request="loadDataTable"
          :loading="true"
          @select-change="handleTableRowSelect"
        >
          <template #avatar="{ index, row}">
            <Avatar :userId="row.id.match(/\d+/)[0]" :avatar="row.qqAvatar" />
          </template>
          <template #space="{ index, row}">
            {{ size2Str(row.useSpace) }} / {{ size2Str(row.totalSpace) }}
          </template>
          <template #status="{ index, row}">
            <span v-if="row.status == 1" style="color: #529b2e">启用</span>
            <span v-if="row.status == 0" style="color: #f56c62">禁用</span>
          </template>
          <template #op="{ index, row }">
            <el-button link type="primary" style="font-size: 12px;" @click.stop="updateSpace(row)">分配空间</el-button>
            <el-divider direction="vertical" />

            <el-button :type="row.status == 0 ? 'success' : 'danger'"  link style="font-size: 12px;" @click="open(row)">{{ row.status == 0 ? '启用': '禁用' }}</el-button>

<!--            <el-button link :type="row.status == 0 ? 'success' : 'danger'" style="font-size: 12px;" @click.stop="updateUserStatus(row)">-->
<!--              {{ row.status == 0 ? '启用': '禁用' }}-->
<!--            </el-button>-->
          </template>
        </BasicTable>
      </div>
    </div>
  </div>
  <Dialog
      :show="dialogConfig.show"
      :title="dialogConfig.title"
      :buttons="dialogConfig.buttons"
      width="500px"
      :showCancel="false"
      @close="dialogConfig.show = false"
  >
    <el-form
        :model="formData"
        :rules="rules"
        ref="formDataRef"
        label-width="80px"
        @submit.prevent
    >
      <el-form-item label="昵称">{{ formData.nickname }}</el-form-item>
      <el-form-item label="空间大小" prop="changeSpace">
        <el-input
            clearable
            placeholder="请输入空间大小"
            v-model="formData.changeSpace"
        >
          <template #suffix>GB</template>
        </el-input>
      </el-form-item>
    </el-form>
  </Dialog>
</template>

<script>
export default {
    name: "UserSetting",
}
</script>
<script setup>
import { reactive, ref,nextTick } from 'vue'
import { columns } from './columns'
import { BasicTable } from '@/components/Table';
import Avatar from '@/components/Avatar.vue';
import { size2Str } from '@/utils';
import axios from "axios";
import Dialog from "@/components/Dialog.vue";

import { h } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'

const open = (row) => {
  const action = row.status == 0 ? "启用" : "禁用";
  ElMessageBox.confirm(
      `你确定要【${action}】此账号吗？`,
      'Warning',
      {
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel',
        type: 'warning',
        center: true,
      }
  )
      .then(() => {
        ElMessage({
          type: 'success',
          message: '修改完成',
        });
        let numericId = row.id.match(/\d+/)[0];
        return axios.post("/api/admin/updateUserStatus", {
          userId: numericId,
          status: row.status == 0 ? 1 : 0,
        });
      })
      .then(response => {
        if (response.data) {
          loadDataTable();
        } else {
          console.error("修改失败");
        }
      })
      .catch(error => {
        console.error("修改失败");
      });
}

const userTableRef = ref()
const selectedRow = ref([])
const canEditColumns = ref(columns)
const searchParam = reactive({
  select: '',
  filename: ''
})

const dialogConfig = ref({
  show: false,
  title: "修改空间大小",
  buttons: [
    {
      type: "primary",
      text: "确定",
      click: (e) => {
        submitForm();
      },
    },
  ],
});
const formData = ref({});
const formDataRef = ref();
const rules = {
  changeSpace: [{ required: true, message: "请输入空间大小" }],
};

const submitForm = () => {
  axios.post("/api/admin/updateUserSpace", {
    userId: formData.value.id,
    changeSpace: formData.value.changeSpace,
  }).then(response => {
    if (response.data.code === -1) {
      ElMessage({
        type: 'error',
        message: response.data.message,
      });
    } else {
      ElMessage({
        type: 'success',
        message: '修改成功',
      });
      dialogConfig.value.show = false;
    }
  }).catch(error => {
    ElMessage({
      type: 'error',
      message: "修改失败",
    });
  });
}

const loadDataTable = async () => {
  let baseParam = {
    pageNo: 1,
    pageSize: 30
  }
  try {
    const response = await axios.post('/api/admin/loadUserList', baseParam);
    const data = response.data;

    // 将后端返回的数据赋值给records
    return {
      records: data.map(item => ({
        id: item.id,
        avatar: item.qqAvatar,
        nickname: item.nickname,
        email: item.email,
        useSpace: item.useSpace,
        totalSpace: item.totalSpace,
        createTime: item.createTime,
        lastLoginTime: item.lastLoginTime,
        status: item.status
      }))
    };
  } catch (error) {
    ElMessage({
      showClose: true,
      message: '无操作权限，请联系管理员',
      type: 'error'
    });
    return {
      pages: 0,
      records: []
    };
  }
}


function reloadTable() {
  userTableRef.value?.reload()
}


const handleTableRowSelect = (rows) => {
  if (rows.length > 0) {
    canEditColumns.value[0].label = `已选中${rows.length}个用户`
  } else {
    canEditColumns.value[0].label = '昵称'
  }
  const newRows = rows.map((item) => {
    item.id = item.userId + '_' + item.id
    return item
  })
  selectedRow.value = newRows
}

const updateSpace = (row) => {
  dialogConfig.value.show = true;
  nextTick(() => {
    formDataRef.value.resetFields();
    formData.value = Object.assign({}, row);
  });
}


const updateUserStatus = (row) => {
  console.log(row.id)
  const confirmation = window.confirm(
      `你确定要【${row.status == 0 ? "启用" : "禁用"}】吗？`
  );
  if (confirmation) {
    axios.post("/api/admin/updateUserStatus", {
      userId: row.id,
      status: row.status == 0 ? 1 : 0,
    }).then(response => {
      if (response.data) {
        loadDataTable();
      } else {
        console.error("Failed to update user status.");
      }
    }).catch(error => {
      console.error("Error:", error);
    });
  }
};

const searchFile = () => {}
</script>
<style lang="scss" scoped>
@import '@/assets/filelist.scss';
.main-user {
  padding-top: 20px;
  .main-header {
    height: 40px;
    padding: 4px 24px;
    .actions {
      display: flex;
    }
    .select {
      :deep(.el-input__wrapper) {
        border-radius: 18px;
        padding-left: 15px;
      }
      .status-select :deep(.el-popper) {
        border-radius: 8px;
      }
    }
    .search {
      margin-left: 15px;
      width: 270px;
      margin-right: 16px;
      :deep(.el-input__wrapper) {
        border-radius: 18px;
        padding: 1px 15px;
      }

      .text {
        cursor: pointer;
      }
    }
  }
}
</style>
