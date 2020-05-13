package com.xinmintx.web.controller.system;

import com.xinmintx.system.domain.UnitPhoto;
import com.xinmintx.system.mapper.UnitPhotoMapper;
import com.xinmintx.web.controller.tool.File2OSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/system/api")
public class FileUploadController {

    @Autowired
    private UnitPhotoMapper unitPhotoMapper;

    @Autowired
    private File2OSSUtils file2OSSUtils;

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

    @PostMapping("/photoUpload")
    public String photoUpload(MultipartFile file){
        return file2OSSUtils.fileUploadOSS(file);
    }
    /**
     * 接收打卡音频,图片,广告图片,限时抢购图片到oss
     * @param file  文件
     * @return oss路径
     */
    @PostMapping("/fileUpload/{type}")
    public String fileUpload(MultipartFile file, @PathVariable("type") Integer type) {
       return file2OSSUtils.fileUpload(file,type);
    }

}
