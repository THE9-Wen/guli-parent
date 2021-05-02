package com.wenhao.serviceoss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.wenhao.serviceoss.service.FileService;
import com.wenhao.serviceoss.util.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-02 14:17
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String upload(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        String fileHost = ConstantPropertiesUtil.FILE_HOST;

        String uploadUrl = null;
        OSS ossClient = null;

        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            if (!ossClient.doesBucketExist(bucketName)) {
                //创建bucket
                ossClient.createBucket(bucketName);
                //设置oss实例的访问权限：公共读
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }

            // 获取文件流
            InputStream inputStream = file.getInputStream();

            //构建日期路径：avatar/2019/02/26/文件名
            String filePath = new DateTime().toString("yyyy/MM/dd");

            //文件名：uuid.扩展名
            String original = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            String fileType = original.substring(original.lastIndexOf("."));
            String newName = fileName + fileType;
            //avatar/2021/5/2/UUID.jpg
            String fileUrl = fileHost + "/" + filePath + "/" + newName;

            ossClient.putObject(bucketName,fileUrl, inputStream);

            uploadUrl = "https://" + bucketName + "." + endPoint  + "/" + fileUrl;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭OSSClient。
            if (ossClient != null)
                ossClient.shutdown();
        }
        return uploadUrl;
    }
}
