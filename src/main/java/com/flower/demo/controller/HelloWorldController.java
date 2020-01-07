package com.flower.demo.controller;

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
    @RequestMapping("/hello")
    private String HelloWorld(){
        return "hello";
    }

    @PostMapping("/uploadPicture")
    public void uploadPicture(@RequestParam("file") MultipartFile file){
        try {
            String filePath = "/Volumes/E/WeChatProjects/Picture";
            String fileName = "targetPicture.jpg";
            file.transferTo(new File(filePath +File.separator+ fileName));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @PostMapping("/discriminate")
    public Map<String,String> discriminate(){
        Map<String,String> resultMap = new HashMap<>();
        String cmd = "ping www.baidu.com";
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            InputStream inputStream = process.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream,"GBK");
            BufferedReader br = new BufferedReader(reader);
            String content = br.readLine();
            while (content != null){
                System.out.println(content);
                content = br.readLine();
            }
            resultMap.put("result","success");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("result","fail");
            resultMap.put("reason",e.getMessage());
        }
        return resultMap;
    }
}
