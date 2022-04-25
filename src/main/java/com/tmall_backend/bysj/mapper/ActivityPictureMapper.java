package com.tmall_backend.bysj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.tmall_backend.bysj.entity.ActivityPicture;

/**
 * @author LiuWenshuo
 * Created on 2022-04-25
 */
@Mapper
@Component
public interface ActivityPictureMapper {
    public Integer insertActivityPic(ActivityPicture picture);
    public ActivityPicture queryActivityPicByID(Integer activityID);
}
