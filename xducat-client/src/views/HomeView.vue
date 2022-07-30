<template>

  <div class="main" >

    <!-- 轮播图 -->
    <el-carousel :interval="4000" type="card" height="250px">

    <el-carousel-item v-for="(item,index) in cat_img" :key="index">
      <el-image :src="item.src" :fit="cover"></el-image>
    </el-carousel-item>

    </el-carousel>

    <!-- 分割线 -->
    <el-divider>
      <h2>
        猫主子秘史<button id="refresh" @click="shuffle()"><i class="el-icon-refresh-left" /></button>
      </h2>
          
    </el-divider>

    <!-- 新闻 -->
    <table align="center" style="margin-top:50px;">
      <tr><td>
        <el-space wrap>
          <el-card shadow="hover" v-for="item in news.slice(5)" :key="item">

            <template #header>
              <div class="card-header">
                <span>{{item.title}}</span>
              </div>
            </template>

            <div class="description">{{item.description}}</div>

          </el-card>
        </el-space>

      </td></tr>
    </table>



  </div>

  <!-- 底栏 -->
  <el-divider>我是猫猫的小尾巴</el-divider>

    <div class="footer">
      <p>Web工程小组全体成员</p>
      <p>图片来源于网上,如有侵权行为请告知</p>
    </div>

</template>



<script lang="ts" >
import { defineComponent } from "vue";
import { findNews } from "../api/index";
export default defineComponent({

  data(){
    return{

      // 轮播图
      cat_img: [
        { src: require("@/assets/img/carousel/1.jpg") },
        { src: require("@/assets/img/carousel/2.jpg") },
        { src: require("@/assets/img/carousel/3.jpg") },
        { src: require("@/assets/img/carousel/4.jpg") },
        { src: require("@/assets/img/carousel/5.jpg") },
        { src: require("@/assets/img/carousel/6.jpg") }
      ],

      news:[]

    }
  },


  created(){
    document.title="西电流浪猫之家 XDUcat"
    this.getNews();
  },

  methods:{

    async getNews(){
      let res = await findNews();
      this.news=res;
    },

    shuffle(){
      var arr=this.news;
      var l = arr.length;
      var index, temp;
      while(l>0){
          index = Math.floor(Math.random()*l);
          temp = arr[l-1];
          arr[l-1] = arr[index];
          arr[index] = temp;
          l--;
      }
      this.news=arr;
      }
  }
});
</script>


<!--轮播图-->
<style scoped>

.el-carousel{
  margin:auto;
  top:30px;
  margin-bottom:50px;
}

.el-image{
  height: 250px;
  width: 100%;
}
</style>


<style>

.footer{
  margin: auto;
  font-size:12px;
  color:gray;
  text-align: center;
}

.description{

  text-overflow:ellipsis;/*超出部分省略号表示*/
  overflow:hidden;/*超出部分隐藏*/
  width: 180px;/*设置显示的最大宽度*/
  height: 120px;
  font-size: 13px;
}

#refresh{
  background-color: white;
  border:none;
}

#refresh:hover{
  cursor:pointer;
}

</style>