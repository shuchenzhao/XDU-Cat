package com.web.xducatserver.controller;

import com.web.xducatserver.Entity.WithCodeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
public class ImgUploadController {
    /**
     * 时间格式化
     */

    /**
     * 图片保存路径，自动从yml文件中获取数据
     *   示例： E:/images/
     */
    @Value("${file-save-path}")
    private String fileSavePath;

    @PostMapping(path = "/upload")
    public WithCodeMessage<String> uploadFile(MultipartFile file, HttpServletRequest request) {
        //1.后半段目录：  2020/03/15
        //都放一个文件夹里了，不区分每天上传的了
        WithCodeMessage<String> wcm=new WithCodeMessage<String>();
        /**
         *  2.文件保存目录  E:/images/2020/03/15/
         *  如果目录不存在，则创建
         */
        File dir = new File(fileSavePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        log.info("图片上传，保存位置：" + fileSavePath );
        //3.给文件重新设置一个名字
        //后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String newFileName= UUID.randomUUID().toString().replaceAll("-", "")+suffix;
        //4.创建这个新文件
        File newFile = new File(fileSavePath  + newFileName);
        //5.复制操作
        try {
            file.transferTo(newFile);

            log.info("图片上传：" + newFileName);
            wcm.setCode("200");
            wcm.setMsg("success");
            wcm.setData(newFileName);
            return wcm;
        } catch (IOException e) {
            wcm.setCode("500");
            wcm.setMsg("fail");
            wcm.setData(null);
            return wcm;
        }
    }
}