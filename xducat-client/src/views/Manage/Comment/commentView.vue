<template>

  <div>
    <el-input v-model="catId"
              placeholder="请输入猫咪ID, 按回车以搜索"
              @change="onSearch"/>
    <el-card v-if="commentList.length&&flag">
      <el-table :data="commentList">
        <el-table-column v-for="col in columnList"
                         :key="col.prop"
                         :prop="col.prop"
                         :label="col.label" />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button size="small"
                       type="danger"
                       @click="Handledelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-card v-else-if="!commentList.length&&flag">该条目下暂无评论</el-card>
  </div>
</template>

<script lang="js">
//import { Search } from "@element-plus/icons-vue";
import { ElMessage } from "element-plus";
import { findCommentByCatId, deleteComment } from "@/api/index";

export default {
  data() {
    return {
      catId: "",
      commentList:[],
      flag:false,
      columnList: [
        { prop: "id", label: "猫咪ID" },
        { prop: "commentid", label: "评论ID" },
        { prop: "comment", label: "评论内容" },
        { prop: "name", label: "评论作者" },
        { prop: "date", label: "评论时间" }
      ],  
    };
  },
  methods: {
    onSearch() {
      findCommentByCatId(this.catId).then((res) => {
        this.flag=true;
        //console.log(res);
        this.commentList=res;
      });
    },
    Handledelete(row) {
      console.log(row.id,row.commentid);
      deleteComment(row.id, row.commentid).then((res)=>{
        console.log(res);
        if(res.data==true){
          ElMessage({
          message: "删除成功!",
          type: "success",
          });
          this.$router.replace("/refresh"); //刷新页面立即看到结果
        }
        else{
          ElMessage.error("删除失败");
        }
      });
    },
  },
};
</script>