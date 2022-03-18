package com.tmall_backend.bysj.service.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.entity.Category;
import com.tmall_backend.bysj.mapper.CategoryMapper;
import com.tmall_backend.bysj.service.category.dto.CategoryDTO;


/**
 * @author LiuWenshuo
 * Created on 2022-03-06
 */
@Service
public class CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 递归查询整个品类的分层结构
     */
    public List<CategoryDTO> queryCategoryMap() {
        List<CategoryDTO> result = new ArrayList<>();
        List<Category> allCategory = categoryMapper.queryAllCategory();
        for (Category category : allCategory) {
            if (category.getParentCategoryID() == 0) {
                CategoryDTO categoryDTO = new CategoryDTO(category);
                getSubCategoryRecurse(categoryDTO, allCategory);
                result.add(categoryDTO);
            }
        }
        return result;
    }
    private CategoryDTO getSubCategoryRecurse(CategoryDTO parent, List<Category> allCategory) {
        for (Category category : allCategory) {
            if (category.getParentCategoryID().equals(parent.getId())) {
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(getSubCategoryRecurse(new CategoryDTO(category), allCategory));
            }
        }
        return parent;
    }

    /**
     * 通过层级查询层级下所有的品类
     * @param level
     * @return
     */
    public List<Category> queryCategoryByLevel(Integer level) {
        return categoryMapper.queryCategoryByLevel(level);
    }

    /**
     * 查询一个品类的所有子品类
     * @param id
     * @return
     */
    public List<Category> querySubCategoryById(Integer id) throws BizException{
        if(categoryMapper.queryCategoryById(id) == null) {
            throw new BizException(ErrInfo.CATEGORY_ID_NOT_EXISTS);
        }
        return categoryMapper.querySubCategoryById(id);
    }

    /**
     * 查询一个品类的父品类
     * @param id
     * @return
     */
    public Category getParentCategoryById(Integer id) throws BizException{
        if(categoryMapper.queryCategoryById(id) == null) {
            throw new BizException(ErrInfo.CATEGORY_ID_NOT_EXISTS);
        }
        return categoryMapper.queryParentCategoryById(id);
    }

    /**
     * 给一个品类的商品数+1,这里做了CAS防并发
     * @param id
     * @return
     */
    public Integer increaseOrDecreaseCategoryCommodityNum(Integer id, Integer num) {
        return categoryMapper.increaseOrDecreaseCategoryCommodityNum(id, num);
    }

    /**
     * 上架/下架一个商品时，品类自底向上的商品数量都+1
     * @return
     */
    public Integer increaseOrDecreaseComNumToRoot(Integer id, Integer num) {
        // 只要还没到根结点,就继续
        if (id == 0) {
            return 0;
        }
        categoryMapper.increaseOrDecreaseCategoryCommodityNum(id, num);
        return increaseOrDecreaseCategoryCommodityNum(categoryMapper.queryCategoryById(id).getParentCategoryID(), num);
    }
}
