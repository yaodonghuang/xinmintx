package com.xinmintx.system.domain;

import java.io.File;

/**
 * @ClassName:.Upload
 * @author:chf
 * @Date:2020/1/3：14:05
 * @developerKits： win 10     jdk1.8
 */
public class Upload {
    private File img;
    private String imgFileName;

    public File getImg() {
        return img;
    }

    public void setImg(File img) {
        this.img = img;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }
}
