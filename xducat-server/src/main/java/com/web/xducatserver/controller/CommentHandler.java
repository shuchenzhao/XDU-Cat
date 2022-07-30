package com.web.xducatserver.controller;

import com.web.xducatserver.Entity.Comment;
import com.web.xducatserver.Entity.WithCodeMessage;
import com.web.xducatserver.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.web.xducatserver.TextCensor.TextCensor;
import org.json.JSONObject;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentHandler {

    @Autowired
    CommentRepository commentRepository;

    @RequestMapping(value="/findCommentByCatId/{Id}")
    public List<Comment> findCommentByCatId(@PathVariable Integer Id){
        return commentRepository.findCommentByCatId(Id);
    }

    @RequestMapping(value = "/insertComment/{id}/{comment}/{name}")
    public WithCodeMessage<Boolean> insertComment(@PathVariable Integer id,@PathVariable String comment, @PathVariable String name) {
        WithCodeMessage<Boolean> wcm=new WithCodeMessage<Boolean>();
        try{
//          先看看评论是否合规
            String result=TextCensor.TextCensor(comment);
            JSONObject jsonObject = new JSONObject(result);
            if(jsonObject.getString("conclusion").equals("不合规")){
                wcm.setData(false);
                wcm.setCode("500");
                wcm.setMsg("Sensitive information");
                return wcm;
            }
            else{
                java.util.Date nowDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(nowDate.getTime());
                commentRepository.insertComment(id,comment,name,sqlDate);
            }
        }catch (Exception e){
            wcm.setData(false);
            wcm.setCode("500");
            wcm.setMsg("fail");
            return wcm;
        }

        wcm.setData(true);
        wcm.setCode("200");
        wcm.setMsg("success");
        return wcm;
    }

    @RequestMapping(value = "/deleteComment/{id}/{commentId}")
    public WithCodeMessage<Boolean> deleteComment(@PathVariable Integer id, @PathVariable Integer commentId) {
        WithCodeMessage<Boolean> wcm=new WithCodeMessage<Boolean>();
        try{
            Comment cm=null;
            cm=commentRepository.findCommentExtend(id,commentId);
            if(cm==null){
                wcm.setData(false);
                wcm.setCode("404");
                wcm.setMsg("fail");
                return wcm;
            }
            else{
                commentRepository.deleteComment(id,commentId);
                wcm.setData(true);
                wcm.setCode("200");
                wcm.setMsg("success");
                return wcm;
            }
        }catch (Exception e){
            wcm.setData(false);
            wcm.setCode("500");
            wcm.setMsg("fail");
            return wcm;
        }
    }
}

