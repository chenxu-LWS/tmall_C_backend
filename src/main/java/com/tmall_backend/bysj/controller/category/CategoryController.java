package com.tmall_backend.bysj.controller.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmall_backend.bysj.common.ReturnListObject;
import com.tmall_backend.bysj.common.ReturnObject;
import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.entity.Category;
import com.tmall_backend.bysj.service.category.CategoryService;


/**
 * @author LiuWenshuo
 * Created on 2022-03-06
 */
@Controller
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/queryMap")
    @ResponseBody
    public ReturnListObject queryCategoryMap() {
        return new ReturnListObject(true, new ArrayList<>(categoryService.queryCategoryMap()), 0);
    }

    @RequestMapping("/querySubById")
    @ResponseBody
    public ReturnListObject querySubCategoryById(Integer id) {
        if (id == null) {
            return new ReturnListObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            List<Category> subCategoryById = categoryService.querySubCategoryById(id);
            return new ReturnListObject(true, new ArrayList<>(subCategoryById), 0);
        } catch (BizException e) {
            return new ReturnListObject(e);
        }
    }

    @RequestMapping("/queryParentById")
    @ResponseBody
    public ReturnObject queryParentCategoryById(Integer id) {
        if (id == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final Category parentCategory = categoryService.getParentCategoryById(id);
            return new ReturnObject(true, parentCategory, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @RequestMapping("/queryByLevel")
    @ResponseBody
    public ReturnListObject queryByLevel(Integer level) {
        if (level == null) {
            return new ReturnListObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final List<Category> categories = categoryService.queryCategoryByLevel(level);
            return new ReturnListObject(true, new ArrayList<>(categories), 0);
        } catch (BizException e) {
            return new ReturnListObject(e);
        }
    }
}
