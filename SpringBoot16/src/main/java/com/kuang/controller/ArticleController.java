package com.kuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.kuang.bean.Article;
import com.kuang.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleMapper articleMapper;
    @GetMapping("/toEditor")
    public String toEditor(){
        return "editor";
    }

    @PostMapping("/addArticle")
    public String addArticle(Article article){
        articleMapper.addArticle(article);
        return "editor";
    }


    //博客图片上传问题
    @RequestMapping("/file/upload")
    @ResponseBody
    public JSONObject fileUpload(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request) throws IOException {
        //上传路径保存设置

        //获得SpringBoot当前项目的路径：System.getProperty("user.dir")
        String path = System.getProperty("user.dir")+"/upload/";

        //按照月份进行分类：
        Calendar instance = Calendar.getInstance();
        String month = (instance.get(Calendar.MONTH) + 1)+"月";
        path = path+month;

        File realPath = new File(path);
        if (!realPath.exists()){
            realPath.mkdirs();
        }

        //上传文件地址
        System.out.println("上传文件保存地址："+realPath);

        //解决文件名字问题：我们使用uuid;
        String filename = "ks-"+ UUID.randomUUID().toString().replaceAll("-", "");
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(new File(realPath +"/"+ filename));

        //给editormd进行回调
        JSONObject res = new JSONObject();
        res.put("url","/upload/"+month+"/"+ filename);
        res.put("success", 1);
        res.put("message", "upload success!");

        return res;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        Article article = articleMapper.getArticleById(id);
        model.addAttribute("article",article);
        return "article";
    }
}