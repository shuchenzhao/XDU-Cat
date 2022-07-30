<template>
  <div>
    <el-backtop :right="50" :bottom="100" />
    <el-space direction="vertical" alignment="left"  wrap>
      <!-- 先对现有猫猫进行展示 -->
      <el-row>
        <el-col
          v-for="(cat, index) in cats"
          :key="cat.id"
          :span="5"
          :offset="1"
        >
          <div class="col">
            <el-card>
           
              <img
                class="image"
                :src="require('../../../assets/img/catProfile/' + cat.img)"
                alt="猫咪图片"/>
                
              <div style="padding: 14px">
                <span>猫咪颜色：{{ cat.color }}</span>
                <br /><br />
                <!-- <el-row class="editLocation"> -->
                <span v-show="isEdit[cat.id]">地点：{{ cat.location }}</span>
                <el-input
                  ref="inputLocation"
                  v-show="!isEdit[cat.id]"
                  type="text"
                  v-model="location"
                  @keyup.enter="handleEnter(cat)"
                />
                <el-button
                  class="btnEdit"
                  type="primary"
                  plain
                  circle
                  size="small"
                  v-show="isEdit[cat.id]"
                  @click="editLocation(cat, index)"
                >
                  <el-icon>
                    <Edit />
                  </el-icon>
                </el-button>
                <!-- </el-row> -->
              </div>
            </el-card>
          </div>
        </el-col>
      </el-row>

      <el-divider />

      <!-- 然后可以新增猫猫 -->
      <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        class="demo-ruleForm"
        :size="formSize"
        :label-position="right"
        label-width="120px"
      >
        <el-form-item label="颜色" prop="color">
          <el-input v-model="ruleForm.color" placeholder="请输入猫猫颜色~" />
        </el-form-item>
        <el-form-item label="最近出现地点" prop="location">
          <el-select
            v-model="ruleForm.location"
            placeholder="选择猫猫最近活动地点"
          >
            <el-option label="丁香附近" value="丁香" />
            <el-option label="竹园附近" value="竹园" />
            <el-option label="海棠附近" value="海棠" />
            <el-option label="综合楼附近" value="综合楼" />
            <el-option label="大活附近" value="大活" />
            <el-option label="信远楼附近" value="信远" />
            <el-option label="未知" value="未知" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <!-- <input type="file" @change="getImg" /> -->
          <el-upload
            ref="uploadImgRef"
            action="http://localhost:8888/upload"
            accept=".jpg"
            :on-success="handleSuccess"
            :on-error="handleError"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :limit="1"
            list-type="picture"
          >
            <el-button>点击选择图片</el-button>
            <template #tip>
              <div class="el-upload__tip">
                选<strong>一个</strong>角度最佳的照片进行上传哦~
                <!-- jpg/png files with a size less than 500KB. -->
              </div>
            </template>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm(ruleFormRef)"
            >增加猫猫</el-button
          >
          <el-button @click="resetForm(ruleFormRef)">重置信息</el-button>
        </el-form-item>
      </el-form>
    </el-space>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, reactive, ref } from "vue";
import { FormInstance } from "element-plus";
import { Edit } from "@element-plus/icons-vue";
// import { ElMessage } from "element-plus";


import type { UploadProps, UploadUserFile } from "element-plus";
const formSize = ref("default");
const ruleFormRef = ref<FormInstance>();

const uploadImgRef = ref(null);
// onMounted(() => {
//   console.log(uploadImgRef.value);
// });

import { insertCat, upload } from "@/api/index";
// 要收集的猫猫信息
const ruleForm = reactive({
  color: "",
  location: "",
  img: "",
});

// 成功上传
const handleSuccess: UploadProps["onSucess"] = (res, uploadFile) => {
  console.log(res);
  ElMessage({
    message: "成功上传",
    type: "success",
  });
  ruleForm.img = res.data;
  // location.reload();//bug的源泉
};

// 上传失败
const handleError: UploadProps["onError"] = (err, uploadFile) => {
  ElMessage({
    message: "上传失败，请重试",
    type: "error",
  });
};

// 表单验证规则
const rules = reactive({
  color: [
    { required: true, message: "请输入猫猫颜色!", trigger: "blur" },
    { min: 2, max: 6, message: "长度应在2~6之间", trigger: "blur" },
  ],
  location: [
    {
      required: true,
      message: "请选择猫猫活动区域!",
      trigger: "change",
    },
  ],
});

// 提交表单
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate(async (valid, fields) => {
    if (valid) {
      let res = await insertCat(
        ruleForm.location,
        ruleForm.color,
        ruleForm.img
      );
      ElMessageBox.alert("成功增加猫猫！", "success");
      // 重置表单
      resetForm(formEl);
      // 清除已上传的文件列表
      uploadImgRef.value.clearFiles();
      // 再次请求数据
    } else {
      console.log("error submit!", fields);
    }
  });
};

// 重置表单
const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;

  formEl.resetFields();
};
</script>


<script lang="ts">
import { ElMessage, ElMessageBox } from "element-plus";
import { findAllCat, modifyCat } from "@/api/index";
export default {
  data() {
    return {
      cats: [],
      isEdit: [],
      location: "",
    };
  },
  created() {
    this.getCats();
  },
  methods: {
    async getCats() {
      this.cats = await findAllCat();
      // 在拿到cats数据之后,再初始化isEdit
      this.cats.forEach((el) => {
        this.isEdit[el.id] = true;
      });
    },
    // 点击edit按钮
    editLocation(cat, index) {
      // 点击之后，修改isEdit
      this.isEdit[cat.id] = !this.isEdit[cat.id];
      // 初始化location
      this.location = cat.location;

      // 获取到id对应的input，input自动聚焦
      this.$nextTick(() => {
        this.$refs.inputLocation[index].focus();
      });
    },
    // 按下enter完成修改
    async handleEnter(cat) {
      this.isEdit[cat.id] = !this.isEdit[cat.id];
      // 如果输入为空
      if (this.location == "") {
        // 提示输入不能为空
        ElMessage({
          message: "location不能为空",
          type: "warning",
        });
        this.location = cat.location;
      }
      // 把location更新到cat的location上
      cat.location = this.location;
      // 把修改同步到服务器上
      await modifyCat(cat.id, cat.location);
    },
  },
};
</script>

<style lang="css">
.el-main {
  margin-bottom: 10px;
}

.button {
  padding: 0;
  min-height: auto;
}

.el-card {
  margin-bottom: 15px;
}

.image {
  width: 100%;
  display: block;
}

.btnEdit {
  margin-left: 10px;
}

</style>

<style scoped>
.el-input{
  width:220px;
}
</style>