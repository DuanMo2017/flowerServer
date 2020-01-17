package com.flower.demo.controller;

import com.flower.demo.service.DiscriminateService;
import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorldController {
    @Autowired
    DiscriminateService discriminateService;

    @RequestMapping("/hello")
    private String HelloWorld(){
        return "hello";
    }

    @PostMapping("/uploadPicture")
    public Map<String,String> uploadPicture(@RequestParam("file") MultipartFile file){
        Map<String,String> resultMap = new HashMap<>();
        try {
            //C:\Users\Administrator\Desktop\timg111.jpg
            String filePath = "C:\\Users\\Administrator\\Desktop";
            String fileName = "timg111.jpg";
            file.transferTo(new File(filePath +File.separator+ fileName));
            resultMap.put("result","success");
            resultMap.put("flowerName",discriminateService.discriminate());
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("result","fail");
            resultMap.put("reason",e.getMessage());
        }
        return resultMap;
    }

}
