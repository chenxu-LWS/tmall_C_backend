package com.tmall_backend.bysj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.tmall_backend.bysj.entity.Activity;

/**
 * @author LiuWenshuo
 * Created on 2022-03-27
 */
@Mapper
@Component
public interface ActivityMapper {
    public Integer insertActivity(Activity activity);
    public Integer updateActivityStatus(@Param("id") Integer id, @Param("status") Integer status);
    public List<Activity> queryAllActivityByPage(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
    public Integer queryAllActivityTotalNum();
    public List<Activity> queryOnlineActivityByPage(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
    public Integer queryOnlineActivityTotalNum();
    public Activity queryActivityById(@Param("id") Integer id);
}
