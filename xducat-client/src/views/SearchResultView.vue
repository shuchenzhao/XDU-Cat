<template>

  <div class="main">

  <el-row>
    <h1>
      搜索结果
      <img class="catPaws" :src="require('@/assets/img/icon/searchResult.png')" alt=""  align="absmiddle">
    </h1>
   
     <el-divider >猫猫撒娇中</el-divider>
  </el-row>


  <el-row v-if="cats.length>0">
    <el-col v-for="item in cats"
      :key="item" :span="5" :offset="index > 0 ? 1 : 0" >
       <el-space wrap>
      <el-card :body-style="{ padding: '10px' }" @click="getDetail(item)">
         <template #header>
            <img
              class="image"
              :src="require('@/assets/img/catProfile/' + item.img)"
              alt="猫咪图片"
            />
        </template>
        <div style="padding: 14px" align="center">
          <span>猫咪颜色：{{ item.color }}</span>
          <br />
          <span>出现地点：{{ item.location }}</span>
        </div>
      </el-card>
      </el-space>
    </el-col>
  </el-row>
  <el-empty v-else description="没有您心仪的猫猫..." />

  </div>
</template>

<script setup>
import { ref } from "vue";
</script>

<script>
import { findCatByColor } from "../api/index";
export default {
  data() {
    return {
      cats: [],
    };
  },

  mounted() {
    this.getResult();
  },

  methods: {
    // 根据参数发起请求获得猫猫
    async getResult() {
      let res = await findCatByColor(this.$route.query.color);
      this.cats = res;
    },

    // 点击猫咪项跳转到详情页面
    getDetail(item) {
      // console.log(item.id);
      this.$router.push({
        path: "/catDetail",
        query: { id: item.id },
      });
    },

  },

  // 当输入的猫改变时，这里刷新页面
  // 这里写的很复杂是因为为了让刷新页面自然些
  watch: {
    $route: {
      handler: function (val, oldVal) {
        if (val.path == oldVal.path) {
          if (val.query.color != oldVal.query.color) {
            this.$router.replace("/refresh");
          }
        }
      },
    },
  },
};
</script>

<style >
.image {
  width: 100%;
  display: block;
  border-radius: 10px;
}

.catPaws{
  width: 138px;
  height: 37px;

}

.el-card{
  cursor:pointer;
  border-radius: 10px;
}

.el-card:hover{
  border: 1px dashed grey;
}

</style>