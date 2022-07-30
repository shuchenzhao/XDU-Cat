<template>
  <el-form
    ref="ruleFormRef"
    :model="ruleForm"
    :rules="rules"
    label-width="120px"
    class="demo-ruleForm"
    :size="formSize"
  >
    <el-form-item label="管理员用户名" prop="name">
      <el-input v-model="ruleForm.name" placeholder="请输入用户名" />
    </el-form-item>
    <el-form-item label="密码" prop="pass">
      <el-input v-model="ruleForm.pass" type="password" autocomplete="off" />
    </el-form-item>
    <el-form-item label="确认密码" prop="checkPass">
      <el-input
        v-model="ruleForm.checkPass"
        type="password"
        autocomplete="off"
      />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm(ruleFormRef)"
        >Create</el-button
      >
      <el-button @click="resetForm(ruleFormRef)">Reset</el-button>
    </el-form-item>
  </el-form>
</template>

<script lang="ts" setup>
import { addManager } from "@/api/index";
import { reactive, ref } from "vue";
import type { FormInstance } from "element-plus";
import { ElMessage } from "element-plus";

const formSize = ref("default");
const ruleFormRef = ref<FormInstance>();

// 表单验证
const validatePass = (rule: any, value: any, callback: any) => {
  if (value === "") {
    callback(new Error("Please input the password"));
  } else {
    if (ruleForm.checkPass !== "") {
      if (!ruleFormRef.value) return;
      ruleFormRef.value.validateField("checkPass", () => null);
    }
    callback();
  }
};
const validatePass2 = (rule: any, value: any, callback: any) => {
  if (value === "") {
    callback(new Error("Please input the password again"));
  } else if (value !== ruleForm.pass) {
    callback(new Error("Two inputs don't match!"));
  } else {
    callback();
  }
};
// 用户信息
const ruleForm = reactive({
  name: "",
  pass: "",
  checkPass: "",
});

// 验证规则
const rules = reactive({
  name: [
    { required: true, message: "请输入用户名", trigger: "blur" },
    { min: 3, max: 10, message: "用户名应该在3~10位", trigger: "blur" },
  ],
  pass: [
    { validator: validatePass, trigger: "blur" },
    { min: 6, max: 20, message: "密码应该在6~20位哦~" },
  ],
  checkPass: [
    { validator: validatePass2, trigger: "blur" },
    { min: 6, max: 20, message: "密码应该在6~20位哦~" },
  ],
});

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate(async (valid, fields) => {
    if (valid) {
      let res = await addManager(ruleForm.name, ruleForm.pass);
      console.log(res);
      if (res.code == 200) {
        ElMessage({
          message: "注册成功！",
          type: "success",
        });
      } else {
        ElMessage({
          message: "用户已存在！",
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
