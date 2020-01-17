package com.flower.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class DiscriminateService {
    @Autowired
    TranlateService tranlateService;

    public String discriminate() throws IOException {
        String batPath = "C:/Users/Administrator/Desktop/discriminate.bat";
        String result="";
        try {
            Process child = Runtime.getRuntime().exec(batPath);
            InputStream in = child.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in,"GBK"));
            String line;
            while((line=bufferedReader.readLine())!=null)
            {
                result= line;
            }
            in.close();
            try {
                child.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            result = "识别失败，请检查后台代码";
            e.printStackTrace();
        }
        return tranlateService.getChineseName(result);
    }
}
