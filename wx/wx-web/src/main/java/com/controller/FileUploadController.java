//package com.controller;
//
//import com.data.WxMediaMapper;
//import com.domain.wx.WxMedia;
//import com.dto.web.UploadFileResp;
//import com.service.WxMediaService;
//import com.service.WxSendMsgService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//
///**
// * Created by Admin on 2016/3/13.
// */
//@Controller
//public class FileUploadController {
//    static Logger logger = LoggerFactory.getLogger(FileUploadController.class);
//    @Resource
//    WxMediaService wxMediaService;
//    @Resource
//    WxMediaMapper wxMediaMapper;
//    @Resource
//    WxSendMsgService wxSendMsgService;
//
//    @RequestMapping(value = "/fileupload.html")
//    public ModelAndView fileupload() {
////        logger.debug("this is a debug log");
//        ModelAndView view = new ModelAndView("/home/fileupload");
//        return view;
//    }
//
//    @RequestMapping(value = "/upload.do", method = RequestMethod.POST)
//    public UploadFileResp upload(@RequestParam("file") MultipartFile file,
//                                 HttpServletRequest req) {
//        UploadFileResp resp = new UploadFileResp();
//
//        String path = req.getServletContext().getRealPath("/upload");
//        try {
//            File newFile = new File(path);
//            if (!newFile.exists() && !newFile.isDirectory()) {
//                System.out.println(path + "目录不存在，需要创建");
//                //创建目录
//                newFile.mkdir();
//            }
//            path = path + "\\" + file.getOriginalFilename();
//            FileCopyUtils.copy(file.getBytes(), new File(path));
//
////            wxMediaService.uploadTmpMedia(path, 1, "title", "remark");
//            wxMediaService.addMaterial(path, 1);
//            resp.setSuccess(true);
//        } catch (IOException e) {
//            e.printStackTrace();
//            resp.setSuccess(false);
//            resp.setErrorMsg(e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return resp;
//    }
//
//    @RequestMapping(value = "/download.html")
//    public void download(HttpServletRequest req, HttpServletResponse resp) throws Exception {
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
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/sendMsg.do", method = RequestMethod.POST)
//    public String sendMsg(@RequestParam("message") String message, HttpServletRequest req) {
//        String str="321";
////        String str = wxMsgReplyService.sendCustomTextMsg("o8MXrsrWROJQIfXa2QH6jSKjnTj8", message, 1);
////        String str=wxSendMsgService.sendCustomImgMsg("o8MXrsrWROJQIfXa2QH6jSKjnTj8","TUhaAIq1lzAqO4QwHzXtqIQHfvjTszrmJn-Tz3ukk4w",1);
//        return str;
//    }
//}
