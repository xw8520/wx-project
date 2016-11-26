package com.controller;

import com.models.web.UploadFileResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * Created by Admin on 2016/3/13.
 */

@Controller
@RequestMapping("file")
public class FileUploadController {
    Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public UploadFileResp upload(@RequestParam("file") MultipartFile file,
                                 HttpServletRequest req) {
        UploadFileResp resp = new UploadFileResp();

        String path = req.getServletContext().getRealPath("/upload");
        try {
            File newFile = new File(path);
            if (!newFile.exists() && !newFile.isDirectory()) {
                logger.debug("目录不存在，需要创建");
                newFile.mkdir();
            }
            String newName = UUID.randomUUID() + file.getOriginalFilename();
            path = path + File.separator + newName;
            FileCopyUtils.copy(file.getBytes(), new File(path));
            resp.setData(newName);
            resp.setSuccess(true);
        } catch (IOException ex) {
            logger.error("", ex);
            resp.setSuccess(false);
            resp.setErrorMsg(ex.getMessage());
        } catch (Exception ex) {
            logger.error("", ex);
        }
        return resp;
    }

    @RequestMapping(value = "download.html")
    public void download(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        String mediaid = "WbG4mcHYNtyYQboahEcQ4QzgMRxlU2dTYvnJabwhxxmuoapf9JfFnmFIRuvMAyN_";
//        WxMedia media = wxMediaMapper.getMediaByMediaId(mediaid);
//        File file = wxMediaService.downLoadTmpMedia(mediaid, 1, media.getFilename());
//        resp.setContentType(req.getServletContext().getMimeType(media.getFilename()));
//        resp.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
//        InputStream inputStream = new FileInputStream(file);
//        OutputStream out = resp.getOutputStream();
//        byte[] buffer = new byte[1024];
//        int size = 0;
//        while ((size = inputStream.read(buffer)) != -1) {
//            out.write(buffer, 0, size);
//        }
//        inputStream.close();
    }
}
