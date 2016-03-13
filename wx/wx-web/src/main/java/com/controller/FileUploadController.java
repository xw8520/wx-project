package com.controller;

import com.dto.web.UploadFileResp;
import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by Admin on 2016/3/13.
 */
@Controller
public class FileUploadController {

    @RequestMapping(value = "/fileupload.html")
    public ModelAndView fileupload() {
        ModelAndView view = new ModelAndView("/home/fileupload");
        return view;
    }

    @RequestMapping(value = "/upload.do", method = RequestMethod.POST)
    public UploadFileResp upload(@RequestParam("file") MultipartFile file,
                                 HttpServletRequest req) {
        UploadFileResp resp = new UploadFileResp();

        String path = req.getServletContext().getRealPath("/upload");
        try {
            File newFile = new File(path);
            if (!newFile.exists() && !newFile.isDirectory()) {
                System.out.println(path + "目录不存在，需要创建");
                //创建目录
                newFile.mkdir();
            }
            FileCopyUtils.copy(file.getBytes(), new File(path + "/"
                    + file.getOriginalFilename()));
            resp.setSuccess(true);
        } catch (IOException e) {
            e.printStackTrace();
            resp.setSuccess(false);
            resp.setErrorMsg(e.getMessage());
        }
        return resp;
    }
}
