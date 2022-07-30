<template>
  <div>
    <el-affix :offset="0">
      <el-menu  id="hearder" :default-active="$route.path" mode="horizontal"  
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        router="true">

        <div id="title">
        
          <img :src="require('@/assets/img/icon/icon.png')" alt="图标">
          西电流浪猫之家 XDUcat
        </div>

          <el-menu-item index="/" >Home</el-menu-item>
          <el-menu-item index="/login" >Login</el-menu-item>
      

        <!-- 控制search的出现 -->
        <div id="search">

          <span  v-if="this.$route.fullPath=='/' || this.$route.fullPath.indexOf('/catDetail')!=-1 || this.$route.fullPath.indexOf('/searchResult')!=-1">
            <el-form  ref="searchRef" :model="search" :inline="true">
              <el-form-item prop="search">
                <el-input  v-model="search.input" size="small" placeholder="输入猫咪颜色搜索" />
              </el-form-item>
              <el-form-item>
                <el-button  size="small" icon="el-icon-search" @click="onSearch(searchRef)" plain>搜索</el-button>    
              </el-form-item>
            </el-form>

          </span>
    
        </div>

      </el-menu>
    </el-affix>
    <router-view />

  </div>
</template>



<script lang="ts" >
import { defineComponent } from "vue";

export default defineComponent({
    

  methods: {
    onSearch() {
      this.$router.push({
        path: "/searchResult",
        query: { color: this.search.input },
      });
    },
  }

  })
</script>

<script lang="ts" setup>
  import { reactive } from "vue";
  const search = reactive({
    input: "",
  });

</script>


<style>
body {
  
    margin: 0;
    background-color: rgb(245, 245, 245);
}



.el-menu{
  min-width: 1300px;
}

.main{
  margin: auto;
  width: 85%;
  min-width: 1200px;
  padding: 0 3.125%;
  background-color: white;
  min-height:calc(100vh - 61px);
  box-shadow: 0px 0px 5px 0px rgba(0, 0, 0, 0.05);
}

.el-button{
  background-color:#FFC125;
}
</style>

<style lang="css" scoped>
a {
  font-size: 20px;
  color: black;
  text-decoration: none;
}

#title{
    float: left;
    font-size: 20px;
    line-height: 60px;
    color: white;
    margin-left: 20px;
    margin-right:20px;
}

#title img{
  margin: 5px;
  float: left;
}

.el-menu-item{
  float: left;
  font-size: 18px;
}
</style>


<style>
#search{
  display: inline-block;
  width: 450px;
  position: relative;
  right:-100px;
  top: 9px;
}

#hearder{
  box-shadow: 0px 0px 5px 0px rgba(0, 0, 0, 0.5);
}


</style>

<style lang="less" scoped>
// 改变input里的字体颜色
/deep/#search input{
  color: gray;
  font-size: 13px;
  width: 250px;
  border-top-left-radius: 20px;
  border-bottom-left-radius: 20px;
  border-top-right-radius: 0px;
  border-bottom-right-radius: 0px;
  }

/deep/#search button{
  position: relative;
  left:-11px;
  top:2px;
  background-color: transparent;
  font-size: 14px;
  color: #ffffff;
  height: 32px;
  border: 1px solid rgba(255, 255, 255, 0.5);
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
  border-top-left-radius: 0px;
  border-bottom-left-radius: 0px;
  border-left:1px solid transparent;
}

/deep/#search button:hover{
  background-color:#8c9eb1;

  translate: 2s;
}


/deep/#search .el-form{
  display:inline-block;
  width: 400px;
  height: 60px;
}

// 改变input框背景颜色
/deep/#search .el-input__inner {
  background-color: rgb(255,255,255);
  border: 1px solid rgba(255, 255, 255, 0.5);
}
</style>