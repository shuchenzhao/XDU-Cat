<template>
  <div class="common-layout">


    <!-- 点击别的路由，刷新后高亮的也是默认设置，不准确 -->
    
   <div id="aside">

        <el-menu 
          :default-active="$route.path"
          class="el-menu-vertical-demo"
          :router="true"
        >
          <el-menu-item index="/manage/editCat">
            <el-icon><icon-menu /></el-icon>
            <span>编辑猫猫信息</span>
          </el-menu-item>
          <el-menu-item index="/manage/addAdmin">
            <el-icon><user /></el-icon>
            <span>添加管理员</span>
          </el-menu-item>
          <el-menu-item index="/manage/password">
            <el-icon><key /></el-icon>
            <span>修改密码</span>
          </el-menu-item>
          <el-menu-item index="/manage/comment">
            <el-icon><setting /></el-icon>
            <span>管理评论</span>
          </el-menu-item>
        </el-menu>
    </div>
      
    <div id="op">
      <router-view/>
    </div>
      


  </div>
</template>

<script lang="ts" setup>
import {
  Document,
  Menu as IconMenu,
  Key,
  User,
  Location,
  Setting,
} from "@element-plus/icons-vue";
import { ref } from "vue";
import router from "@/router";
</script>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "ManegeView",
});
</script>

<style lang="css" scoped>
#aside{
  position: fixed;
  max-width:150px;
  height:calc(100vh - 61px);
  overflow: hidden;
  background-color: white;

  box-shadow: 0px 0px 5px 0px rgba(0, 0, 0, 0.3);
}

#op{
  margin-left:150px;
  min-width: calc(100vh-150px);
  padding:20px;

}

</style>
