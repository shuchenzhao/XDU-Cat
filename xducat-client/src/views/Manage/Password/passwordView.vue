<template>
  <el-form ref="ruleFormRef"
           :model="ruleForm"
           :rules="rules"
           label-width="120px"
           class="demo-ruleForm"
           :size="formSize">
    <el-form-item label="用户名"
                  prop="username">
      <el-input v-model="ruleForm.username" />
    </el-form-item>
    <el-form-item label="旧密码"
                  prop="oldpwd">
      <el-input v-model="ruleForm.oldpwd"
                type="password"
                autocomplete="off" />
    </el-form-item>
    <el-form-item label="新密码"
                  prop="newpwd">
      <el-input v-model="ruleForm.newpwd"
                type="password"
                autocomplete="off" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary"
                 @click="submitForm(ruleFormRef)">确认修改</el-button>
      <el-button @click="resetForm(ruleFormRef)">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script lang="ts" setup>
import { modifyPassword } from "@/api/index";
import { ElMessage } from "element-plus";
import { reactive, ref } from "vue";
import type { FormInstance } from "element-plus";

const formSize = ref("default");
const ruleFormRef = ref<FormInstance>();

const ruleForm = reactive({
  username: "",
  oldpwd: "",
  newpwd: "",
});

// 验证规则
const rules = reactive({
  username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
  oldpwd: [{ required: true, message: "请输入旧密码", trigger: "blur" }],
  newpwd: [
    { required: true, message: "请输入新密码", trigger: "blur" },
    { min: 6, max: 20, message: "密码应该在6~20位哦~" },
  ],
});

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate(async (valid, fields) => {
    if (valid) {
      let res = await modifyPassword(
        ruleForm.username,
        ruleForm.oldpwd,
        ruleForm.newpwd
      );
      console.log(res);
      if (res.data === "success") {
        ElMessage({
          message: "修改成功",
          type: "success",
        });
      } else if (res.data === "wrongUsername") {
        ElMessage({
          message: "用户不存在",
          type: "warning",
        });
      } else if (res.data === "wrongPassword") {
        ElMessage({
          message: "密码错误",
          type: "warning",
        });
      } else {
        ElMessage({
          message: "出错了",
          type: "warning",
        });
      }
    } else {
      console.log("error submit!", fields);
    }
  });
};

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
};
</script>
