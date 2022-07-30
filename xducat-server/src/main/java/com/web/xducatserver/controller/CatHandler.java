package com.web.xducatserver.controller;

import com.web.xducatserver.Entity.Cat;
import com.web.xducatserver.Entity.WithCodeMessage;
import com.web.xducatserver.Repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/cat")
public class CatHandler {
    @Autowired
    CatRepository catRepository;

    @GetMapping("/findAll")
    public List<Cat> findAll(){
        return catRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(value="/findCatById/{id}")
    public WithCodeMessage<Cat> findCatById(@PathVariable("id") int id){
        WithCodeMessage<Cat> wcm=new WithCodeMessage<Cat>();
        wcm.setData(catRepository.findCatById(id));
        wcm.setCode("200");
        wcm.setMsg("success");
        return wcm;
    }

//    输入猫咪颜色得到结果，支持汉字
    @RequestMapping(value="/findCatByColor/{color}")
    public List<Cat> findCatByColor(@PathVariable String color){
    return catRepository.findCatByColor(color);
}

    @RequestMapping(value="/modify/{id}/{location}")
    public WithCodeMessage<Boolean> modifyCat(@PathVariable("id") int id,@PathVariable("location") String location){
        Cat res=null;
        WithCodeMessage<Boolean> wcm=new WithCodeMessage<Boolean>();
        try{
            res=catRepository.findCatById(id);    //在数据库中寻找该id的猫
            if(res==null){
                wcm.setData(false);
                wcm.setCode("404");
                wcm.setMsg("fail");
                return wcm;//猫咪不存在，返回报错
            }

            catRepository.modifyCat(id,location);
            wcm.setData(true);
            wcm.setCode("200");
            wcm.setMsg("success");
            return wcm;

        }
        catch (Exception e)
        {
            wcm.setData(false);
            wcm.setCode("500");
            wcm.setMsg("fail");
            return wcm;//出现错误，返回失败
        }
    }

    @RequestMapping(value="/insert/{location}/{color}/{img}")
    public WithCodeMessage<Boolean> insertCat(@PathVariable("location") String location,@PathVariable("color") String color,@PathVariable("img") String img){
        Cat res=null;
        WithCodeMessage<Boolean> wcm=new WithCodeMessage<Boolean>();
        try{
            int id;
            res=catRepository.newestCat();
            id= res.getId()+1;
            catRepository.insertCat(id,location,color,img);
            wcm.setData(true);
            wcm.setCode("200");
            wcm.setMsg("success");
            return wcm;
        }
        catch (Exception e)
        {
            wcm.setData(false);
            wcm.setCode("500");
            wcm.setMsg("fail");
            return wcm;//出现错误，返回失败
        }
    }
}


