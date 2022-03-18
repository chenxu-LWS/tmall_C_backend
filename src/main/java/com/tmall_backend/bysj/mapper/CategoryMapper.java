package com.tmall_backend.bysj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.tmall_backend.bysj.entity.Category;


/**
 * @author LiuWenshuo
 * Created on 2022-03-05
 */
@Mapper
@Component
public interface CategoryMapper {
    public Category queryCategoryById(Integer id);
    public List<Category> querySubCategoryById(Integer id);
    public Category queryParentCategoryById(Integer id);
    public List<Category> queryAllCategory();
    public List<Category> queryCategoryByLevel(Integer level);

    public Integer deleteCategory(@Param("id") Integer id);
    public Integer increaseOrDecreaseCategoryCommodityNum(@Param("id") Integer id, @Param("num") Integer num);
}
