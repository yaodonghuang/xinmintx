package com.xinmintx.community.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
public class File2OSSUtils {

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.bucketName}")
    private String bucketName;

    @Value("${oss.url}")
    private String url;

    @Value("${oss.images}")
    private String images;

    @Value("${oss.music}")
    private String music;

    @Value("${oss.cardpicture}")
    private String cardpicture;

    @Value("${oss.adpicture}")
    private String adpicture;

    /**
     * 上传图片到oss
     *
     * @param file
     */
    public String fileUploadOSS(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = images + UUID.randomUUID() + "_" + System.currentTimeMillis() + substring;
        OSS ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            ossClient.putObject("hstx", fileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 关闭OSSClient。
        ossClient.shutdown();
        return url + fileName;
    }

    /**
     * 上传打卡音频文件到oss
     *
     * @param file 音频
     * @return oss路径
     */
    public String fileUpload(MultipartFile file, Integer type) {
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = "";
        if (type == 1) {
            fileName = music + UUID.randomUUID() + "_" + System.currentTimeMillis() + substring;
        } else if (type == 2) {
            fileName = cardpicture + UUID.randomUUID() + "_" + System.currentTimeMillis() + substring;
        } else if (type == 3) {
            fileName = adpicture + UUID.randomUUID() + "_" + System.currentTimeMillis() + substring;
        }
        OSS ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            ossClient.putObject("hstx", fileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 关闭OSSClient。
        ossClient.shutdown();
        return url + fileName;
    }

}
