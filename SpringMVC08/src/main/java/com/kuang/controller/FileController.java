package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RequestMapping("/file")
@RestController
//@Controller
public class FileController {

    @RequestMapping("/upload")
    public String uploadFile(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isEmpty(originalFilename)) {
            return "what a pity！ -- 上传失败！";
        }
        //上传路径保存设置
//        F:/project/ks/SpringMVC08/out/artifacts/SpringMVC08_war_exploded/upload
        String uploadPath = request.getServletContext().getRealPath("/upload");
        File fileUpdatePath = new File(uploadPath);
        if (!fileUpdatePath.exists()) {
            fileUpdatePath.mkdirs();
        }
        InputStream inputStream = file.getInputStream();
        String destFileName = uploadPath + "/" + originalFilename;
        FileOutputStream fileOutputStream = new FileOutputStream(destFileName);
        int length;
        byte[] bytes = new byte[1024];
        while ((length = inputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, length);
            fileOutputStream.flush();
        }

        fileOutputStream.close();
        inputStream.close();
        return "beautiful！ -- 上传成功";
    }

    @RequestMapping("/fileTransfer")
    public String uploadFileTransfer(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isEmpty(originalFilename)) {
            return "what a pity！ -- Transfer上传失败！";
        }
        //上传路径保存设置
//        F:/project/ks/SpringMVC08/out/artifacts/SpringMVC08_war_exploded/upload
        String uploadPath = request.getServletContext().getRealPath("/upload");
        File fileUpdatePath = new File(uploadPath);
        if (!fileUpdatePath.exists()) {
            fileUpdatePath.mkdirs();
        }

        File destFile = new File(uploadPath + "/" + originalFilename);
        file.transferTo(destFile);
        return "beautiful！ -- Transfer上传成功";
    }


    @RequestMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = "超1.png";

        // 慢慢下载而不马上打开
        //设置页面不缓存,清空buffer
        response.reset();
        response.setCharacterEncoding("UTF-8");
        //二进制传输数据
        response.setContentType("multipart/form-data");
        //设置响应头
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()));

        String realPath = request.getServletContext().getRealPath("/upload/" + fileName);
        OutputStream outputStream = response.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(realPath);
        byte[] bytes = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
            outputStream.flush();
        }
        outputStream.close();
        fileInputStream.close();
        return "下载成功！";
    }
}
