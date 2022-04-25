package com.tmall_backend.bysj.service.activity_picture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.oss.model.OSSObject;
import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.common.oss.OssClient;
import com.tmall_backend.bysj.entity.Activity;
import com.tmall_backend.bysj.entity.ActivityPicture;
import com.tmall_backend.bysj.mapper.ActivityMapper;
import com.tmall_backend.bysj.mapper.ActivityPictureMapper;

/**
 * @author LiuWenshuo
 * Created on 2022-04-25
 */
@Service
public class ActivityPictureService {

    Logger logger = Logger.getLogger(ActivityPictureService.class);

    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    ActivityPictureMapper pictureMapper;
    @Autowired
    OssClient ossClient;

    private static final String ACTIVITY_BUCKET_NAME = "tmall-activity-pictures";

    public String queryActivityPicByID(Integer activityId) {
        final Activity activity = activityMapper.queryActivityById(activityId);
        if (activity == null) {
            throw new BizException(ErrInfo.ACTIVITY_ID_NOT_EXISTS);
        }
        final ActivityPicture activityPicture = pictureMapper.queryActivityPicByID(activityId);
        if (activityPicture == null) {
            return null;
        }
        final String pictureObj = activityPicture.getPictureObj();
        final OSSObject ossObject = ossClient.getOssClient().getObject(ACTIVITY_BUCKET_NAME, pictureObj);
        BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
        StringBuilder builder = new StringBuilder();
        try {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                builder.append(line);
            }
            reader.close();
            return builder.toString();
        } catch (IOException e) {
            throw new BizException(ErrInfo.OSS_ERROR);
        }
    }

    public List<String> queryActivityPics(Integer number) {
        try {
            final List<ActivityPicture> activityPicture = pictureMapper.queryActivityPics(number);
            List<String> res = new ArrayList<>();
            for (ActivityPicture picture : activityPicture) {
                final String pictureObj = picture.getPictureObj();
                final OSSObject ossObject = ossClient.getOssClient().getObject(ACTIVITY_BUCKET_NAME, pictureObj);
                BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
                StringBuilder builder = new StringBuilder();
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    builder.append(line);
                }
                reader.close();
                res.add(builder.toString());
            }
            return res;
        } catch (IOException e) {
            throw new BizException(ErrInfo.OSS_ERROR);
        }
    }
}
