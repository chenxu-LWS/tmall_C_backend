package com.tmall_backend.bysj.mapper;

import java.util.List;

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
    public List<ActivityPicture> queryActivityPics(Integer number);
}
