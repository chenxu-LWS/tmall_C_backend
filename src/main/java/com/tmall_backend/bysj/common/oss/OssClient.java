package com.tmall_backend.bysj.common.oss;

import org.springframework.stereotype.Service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

/**
 * @author LiuWenshuo
 * Created on 2022-03-16
 */
@Service
public class OssClient {
    private final static String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
    private final static String accessKeyId = "LTAI5tLYqERy5RTMmUCGB68g";
    private final static String accessKeySecret = "mnqNqPjDYyniojcVHJuBoo1IJ05lmt";
    // 创建OSSClient实例。
    OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    public OSS getOssClient() {
        return ossClient;
    }
}
