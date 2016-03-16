package com.controller;

import com.dto.web.UploadFileResp;
import com.dto.wx.enums.TmpMediaType;
import com.service.WxMediaService;
import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by Admin on 2016/3/13.
 */
@Controller
public class FileUploadController {

    @Resource
    WxMediaService wxMediaService;

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
            path = path + "/" + file.getOriginalFilename();
            FileCopyUtils.copy(file.getBytes(), new File(path));

            wxMediaService.uploadTmpMedia(path);
            resp.setSuccess(true);
        } catch (IOException e) {
            e.printStackTrace();
            resp.setSuccess(false);
            resp.setErrorMsg(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
}
