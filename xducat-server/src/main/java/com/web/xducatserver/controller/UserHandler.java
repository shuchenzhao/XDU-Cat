package com.web.xducatserver.controller;

//import com.sun.org.apache.xpath.internal.operations.String;

import com.web.xducatserver.Entity.User;
import com.web.xducatserver.Entity.WithCodeMessage;
import com.web.xducatserver.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    UserRepository userRepository;


    //用户登录，第一个参数是用户名，第二个是密码。类似于
    //http://localhost:8888/user/login/super/123
    @ResponseBody
    @RequestMapping(value="/login/{username}/{password}")
    public WithCodeMessage<User> login(@PathVariable("username") String username, @PathVariable("password") String password){
        User res = null;
        WithCodeMessage<User> wcm=new WithCodeMessage<User>();
        try{
            res=userRepository.findUserByUsername(username);    //在数据库中寻找该用户名的用户
            System.out.println(res);
            String truePassword=res.getPassword();  //获取该用户名对应的真正密码
            if(truePassword.equals(password)==false){  //输入的密码与真正的密码不一致，返回空用户
                wcm.setData(res=null);
                wcm.setCode("404");
                wcm.setMsg("fail");
                return wcm;
            }
            wcm.setData(res);
            wcm.setCode("200");
            wcm.setMsg("success");
            return wcm; //密码正确，返回该用户
        }
        catch (Exception e)
        {
            wcm.setData(res=null);
            wcm.setCode("500");
            wcm.setMsg("fail");
            return wcm;//服务器内部出现错误，返回空用户
        }
    }


    @RequestMapping(value="/addManager/{username}/{password}")
    public WithCodeMessage<Boolean> addManager(@PathVariable("username") String username, @PathVariable("password") String password){
        User res = null;
        WithCodeMessage<Boolean> wcm=new WithCodeMessage<Boolean>();
        try{
            res=userRepository.findUserByUsername(username);    //在数据库中寻找该用户名的用户
            if(res==null){//数据库中不存在这一用户名的用户
                userRepository.addManager(username,password);//添加成功\
                wcm.setData(true);
                wcm.setCode("200");
                wcm.setMsg("success");
                return wcm;//返回成功
            }
            else {
                wcm.setData(false);
                wcm.setCode("404");
                wcm.setMsg("fail");
                return wcm; //已存在这一用户名的用户，添加失败，返回失败
            }
        }
        catch (Exception e)
        {
            wcm.setData(false);
            wcm.setCode("500");
            wcm.setMsg("fail");
            return wcm;//出现错误，返回失败
        }
    }

    @RequestMapping(value="/modifyPassword/{username}/{expassword}/{nowpassword}")
    public WithCodeMessage<String> modifyPassword(@PathVariable("username") String username, @PathVariable("expassword") String expassword, @PathVariable("nowpassword") String nowpassword){
        User res = null;
        WithCodeMessage<String> wcm=new WithCodeMessage<String>();
        try{
            res=userRepository.findUserByUsername(username);    //在数据库中寻找该用户名的用户
            if(res==null) {
                wcm.setData("wrongUsername");
                wcm.setCode("404");
                wcm.setMsg("fail");
                return wcm;//用户名不存在，返回报错
            }
            String truePassword=res.getPassword();  //获取该用户名对应的真正密码
            if(truePassword.equals(expassword)){
                userRepository.modifyPassword(username,nowpassword);
                wcm.setData("success");
                wcm.setCode("200");
                wcm.setMsg("success");
                return wcm;
            }   //旧密码正确，修改成功，返回success
            else {
                wcm.setData("wrongPassword");
                wcm.setCode("404");
                wcm.setMsg("fail");
                return wcm;
            }
        }
        catch (Exception e)
        {
            wcm.setData("error");
            wcm.setCode("500");
            wcm.setMsg("fail");
            return wcm;//出现错误，返回空用户
        }
    }
}
