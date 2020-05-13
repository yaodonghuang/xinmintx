package com.xinmintx.agent.controller;

import com.xinmintx.agent.annotation.DisableAuth;
import com.xinmintx.agent.mapper.UnitPhotoMapper;
import com.xinmintx.agent.model.UnitPhoto;
import com.xinmintx.agent.util.File2OSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/11/16 0016
 * @time: 下午 15:50
 * @Description:
 */
@RestController
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    private UnitPhotoMapper unitPhotoMapper;

    @Autowired
    private File2OSSUtils file2OSSUtils;

    @DisableAuth
    @PostMapping("/imageUpload")
    public UnitPhoto imageUpload(MultipartFile file, HttpServletRequest request){
        String type = request.getParameter("type");
        String photoUrl = file2OSSUtils.fileUploadOSS(file);
        UnitPhoto unitPhoto = new UnitPhoto();
        unitPhoto.setPhotoUrl(photoUrl);
        unitPhoto.setType(type);
        unitPhotoMapper.insertSelective(unitPhoto);
        return unitPhoto;
    }

    @DisableAuth
    @PostMapping("/photoUpload")
    public String photoUpload(MultipartFile file){
        return file2OSSUtils.fileUploadOSS(file);
    }
}
