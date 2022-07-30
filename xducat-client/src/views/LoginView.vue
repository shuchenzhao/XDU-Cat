<template>
  <!-- login收集数据 -->

<div id="container">

  <div id="login">

    <div class="logo">
        <img :src='require("@/assets/img/icon/catLogo.jpg")' alt="">
    </div>


    <el-form ref="loginRef" :model="login" :rules="rules">
      <!-- prop表单验证用户名 -->
      <el-form-item prop="username">
        <el-input v-model="login.username" placeholder="用户名">
          <template #prefix>
            <el-icon class="el-input__icon" id="username" :size="20"><user/></el-icon>
          </template>
        </el-input>
      </el-form-item>
      <!-- prop表单验证密码 -->
      <el-form-item prop="password">
        <el-input
          v-model="login.password"
          type="password"
          placeholder="密码"
          show-password
        >
          <template #prefix>
            <el-icon class="el-input__icon" id="password" :size="20"
              ><lock
            /></el-icon>
          </template>
        </el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" class="loginBtn" @click="onLogin('loginRef')">登录</el-button>
      </el-form-item>
    </el-form>

  </div>

</div>


</template>

<script lang="ts" setup>
import { reactive } from "vue";
import { User, Lock } from "@element-plus/icons-vue";
const login = reactive({
  username: "",
  password: "",
});

const rules = reactive({
  username: [
    {
      required: true,
      message: "请输入用户名",
      trigger: "blur",
    },
  ],
  password: [
    {
      required: true,
      message: "请输入密码",
      trigger: "blur",
    },
  ],
});
</script>

<script lang="ts">
import { managerLogin } from "../api/index";
import { defineComponent } from "vue";
import { ElMessage } from "element-plus";
import router from "@/router";

export default defineComponent({
  methods: {
    async onLogin() {
      // 这是为了判断空用户名和密码而设置的
      // 表单验证判断似乎会出问题，这里单独写了
      var str = this.login.username;
      if (str == "undefined" || !str || !/[^\s]/.test(str)) {
        ElMessage.error("用户名和密码不得为空");
        return;
      }

      str = this.login.password;
      if (str == "undefined" || !str || !/[^\s]/.test(str)) {
        ElMessage.error("用户名和密码不得为空");
        return;
      }

      let res = await managerLogin(this.login.username, this.login.password);
      console.log(res);
      if (res) {
        this.$router.push("/manage/editCat");
        ElMessage({
          message: "登录成功！欢迎您，" + this.login.username,
          type: "success",
        });
      } else {
        ElMessage.error("用户名不存在或者密码错误，请检查!");
      }
      // 验证后调用接口验证用户身份
      // this.$axios
      //   .post(
      //     "http://localhost:8888/user/login/" +
      //       this.login.username +
      //       "/" +
      //       this.login.password
      //   )
      //   .then(
      //     (resp) => {
      //       // 登录成功后动态添加路由
      //       this.$router.push("/manage/editCat");

      //       ElMessage({
      //         message: "登录成功！欢迎您，" + this.login.username,
      //         type: "success",
      //       });
      //       //  else {
      //       //   ElMessage.error("用户名不存在或者密码错误，请检查!");
      //       // }
      //     }
      //     // () => {
      //     //   ElMessage.error("用户名不存在或者密码错误，请检查!");
      //     // }
      // );
    },
  },
});
</script>



<style>
  .loginBtn{
    width: 100%;
  }

  #login{

    position:relative;
    left:0;
    right:0;
    top:25%;
    bottom:0;
    margin:auto;

    border-radius: 20px;
    width: 280px;
    height: 280px;
    box-shadow: 0px 10px 20px 0px rgba(0, 0, 0, 0.2);
    padding:40px;


  }
   
  .logo {
    position: absolute;
    top:20px;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 130px;
    height: 130px;
    border-radius: 50%;
    border: 1px solid #eee;
    padding: 10px;
    background-color: #fff;
    box-shadow: 0 0 10px #ddd;
  }

  .logo img {
    height: 100%;
    width: 100%;
    border-radius: 50%;
    background-color: #eee;
  }

  #login .el-form{
    display: block;
    margin-top:100px;
  }

#container{
  width:100%;
  height: 100vh;
  margin-top:-61px;
  background-image: url("@/assets/img/icon/background.jpg");
  background-size:100% 100%;
}


</style>