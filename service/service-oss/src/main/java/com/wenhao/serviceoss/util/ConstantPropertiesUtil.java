package com.wenhao.serviceoss.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-02 14:11
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {
    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.keyid}")
    private String keyId;

    @Value("${oss.keysecret}")
    private String keySecret;

    @Value("${oss.filehost}")
    private String fileHost;

    @Value("${oss.bucketname}")
    private String bucketName;

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;
    public static String FILE_HOST ;


    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
        FILE_HOST = fileHost;
    }
}
