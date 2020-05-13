package com.xinmintx.hstx.controller;


import com.xinmintx.hstx.util.File2OSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/11/16 0016
 * @time: 下午 15:50
 * @Description:
 */

@RestController
@RequestMapping("/hs/file")
public class FileUploadController {

    @Autowired
    private File2OSSUtils file2OSSUtils;

    /**
     * 图片上传
     * @param file 图片
     * @return 图片地址
     */
    @PostMapping("/imageUpload")
    public String merchantImageUpload(@RequestParam("file") MultipartFile file) {
        return file2OSSUtils.fileUploadOSS(file);
    }
}
