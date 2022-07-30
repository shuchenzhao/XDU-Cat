<template>
  <div class="main">

    <!-- 头图 -->
    <el-row>
      <h1 >
        您召唤的猫猫
        <img :src="require('@/assets/img/icon/cat.png')" alt="" align="absbottom">
      </h1>
      <div id="backgroundImg">
        <div class="frosted-glass" :style="{backgroundImage:'url('+require('@/assets/img/catProfile/'+this.$route.query.id+'.jpg')+')'}"></div>
          <img class="image" :src="require('@/assets/img/catProfile/'+this.$route.query.id+'.jpg')" alt="猫咪图片"/> 
      </div> 
    </el-row>

    
    <!-- 猫咪评论 -->
      <el-divider>和猫咪的点点滴滴</el-divider>
      <h1 >
        评论区
        <i class="el-icon-edit"></i>
      </h1>
      <h4 style="color:grey;" v-if="comments.length === 0">暂时还没有评论哦~快来发表评论吧</h4>
      <el-row v-for="item of comments.slice((currentPage - 1)*pageSize, currentPage*pageSize)" :key="item">
        
        <div class="comment">
          <span class="commentName">{{item.name}}</span>
          <span class="commentDate">{{item.date}}</span>
          <br/>
          <span class="commentInfo">"{{item.comment}}"</span>
        </div>

      </el-row>

      <el-pagination
        background
        :hide-on-single-page = true
        layout="prev,pager,next"
        :total="comments.length"
        :page-size="pageSize"
        :current-page="currentPage"
        @current-change="handleCurrentChange"
      />

      <!-- 写评论 -->
      <el-divider>请铲屎官留言</el-divider>
      <h1>
        留言板
        <i class="el-icon-chat-line-round"></i>
      </h1>
      <div id="writeComment">
        <el-form :model="commentForm">
          <el-form-item label="你的昵称：">
            <el-input size="small" id="nameInput" v-model="commentForm.name" placeholder="如果不填会显示为“西电er”噢~"/>
          </el-form-item>
          <el-form-item label="评论内容：">
            <el-input
              size="small"
              v-model="commentForm.comment"
              :autosize="{ minRows: 2 }"
              type="textarea"
              placeholder="请输入您的评论，不可以超过200字噢~"
              maxlength="200"
              show-word-limit
            />
          </el-form-item>
          <el-form-item>
            <el-button id="submit" size="small" type="primary" @click="onSubmit()">
              <i class="el-icon-chat-dot-round"></i> 提交
            </el-button>
          </el-form-item>
        </el-form>
      </div>


  </div>
</template>


<script lang="ts" setup>
import { reactive } from "vue";
const commentForm = reactive({
  name: "",
  comment: "",
});

</script>

<script lang="ts">
import { findCommentByCatId, insertComment } from "../api/index";
import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      comments: [],
      currentPage: 1, //默认页码为1
      pageSize:5,
    };
  },
  created() {
    this.getComments();
  },
  methods: {
    // 根据猫咪id获取对应评论存储到comments
    async getComments() {
      let res = await findCommentByCatId(this.$route.query.id);
      this.comments = res;
      // console.log(res, "res");
    },

    // 提交评论
    async onSubmit() {
      var name = this.commentForm.name;
      if (name == "") name = "西电er";
      // 提交comment的id、comment内容和评论者的id
      const res = await insertComment(
        this.$route.query.id,
        this.commentForm.comment,
        name
      );
      
    
      console.log(res);
      if (res.data === true) {
        ElMessage({
          message: "留言成功!",
          type: "success",
        });
        this.$router.replace("/refresh"); //刷新页面立即看到留言结果
      } else {
        if(res.msg=="Sensitive information"){
          ElMessage.error("有非法信息,请修改后发表");
        } else ElMessage.error("留言失败!"); //我这里编译不过，先注掉
      }
    },

    handleSizeChange: function (size) {
      //一页显示多少条
      this.pagesize = size;
    },

    handleCurrentChange: function (currentPage) {
      //页码更改方法
      this.currentPage = currentPage;
    }


  },
};
</script>


<!--图片-->
<style scoped>

#backgroundImg{
  width:100%;
  height:400px;
  background-size: 150%;
  overflow:hidden;
}

.image{
  width:300px;
  height:300px;
  position:relative;
  left:50%;
  bottom:50%;
  transform: translate(-50%, -50%);
  box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.5);
}

.frosted-glass {
    width:100%;
    height:400px;
    filter: blur(20px) brightness(150%) opacity(80%);
    background-size: cover;
    transform: scale(1.1);
  
}
</style>

<!--评论-->
<style>

.comment{
  border-left:4px solid rgb(172, 172, 172);
  width: 100%;
  padding:5px 20px;
  margin: 0 15px;
}
.comment:hover{
  background-color: rgb(245, 245, 245);
}

.commentName{
  font-size:17px;
  font-weight:bolder;
}

.commentDate{
  display:inline-block;
  font-size:12px;
  color:gray;
  line-height:18px;
  margin-left:20px;
}

.commentInfo{
  display:inline-block;
  font-size:14px;
  margin-top:5px;
  color:rgb(22, 22, 22);
}

</style>

<!-- 写评论-->
<style>
#writeComment{
  margin: 0 43px;
  margin-right:200px;
}

#submit{
  margin-left:80px;
  font-size:14px;
}
#nameInput{
  width:250px;
}
</style>