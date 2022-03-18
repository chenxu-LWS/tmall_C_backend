package com.tmall_backend.bysj.service.commodity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.common.page.PageBean;
import com.tmall_backend.bysj.entity.Category;
import com.tmall_backend.bysj.entity.Commodity;
import com.tmall_backend.bysj.mapper.BrandMapper;
import com.tmall_backend.bysj.mapper.CategoryMapper;
import com.tmall_backend.bysj.mapper.CommodityMapper;
import com.tmall_backend.bysj.service.commodity.dto.CommodityDTO;

/**
 * @author LiuWenshuo
 * Created on 2022-03-18
 */
@Service
public class CommodityService {
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 通过品类ID，得到品类相关的
     * @param categoryId
     * @return
     */
    public Map<String, Set<String>> queryPropsByCategoryId(Integer categoryId) throws BizException {
        // 参数校验
        if (categoryMapper.queryCategoryById(categoryId) == null) {
            throw new BizException(ErrInfo.CATEGORY_ID_NOT_EXISTS);
        }
        // 先查询有没有子类别,放到一个list里，层序遍历图
        List<Integer> categories = new ArrayList<>();
        Queue<Integer> temp = new LinkedBlockingDeque<>();
        temp.add(categoryId);
        while (!temp.isEmpty()) {
            final Integer curr = temp.remove();
            categories.add(curr);
            final List<Category> subs = categoryMapper.querySubCategoryById(curr);
            for (Category sub : subs) {
                temp.add(sub.getId());
            }
        }
        // 查询该品类下所有的属性
        final List<Commodity> commodities = commodityMapper.queryCommodityByCategoryId(categories);
        Map<String, Set<String>> result = new HashMap<>();
        try {
            for (Commodity commodity : commodities) {
                final Map<String, Object> properties = JSON.parseObject(commodity.getProps());
                properties.forEach((k, v) -> {
                    if (result.containsKey(k)) {
                        final Set<String> set = result.get(k);
                        set.add((String) v);
                        result.put(k, set);
                    } else {
                        final Set<String> set = new HashSet<>();
                        set.add((String) v);
                        result.put(k, set);
                    }
                });
            }
            return result;
        } catch (Exception e) {
            throw new BizException(ErrInfo.COMMODITY_PROP_FORMAT_ERROR);
        }
    }

    /**
     * 分页查询所有的商品
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageBean<CommodityDTO> queryAllByPage(Integer pageNo, Integer pageSize) {
        final List<Commodity> commodities = commodityMapper.queryAllByPage(pageNo * pageSize, pageSize);
        PageBean<CommodityDTO> result = new PageBean<>();
        result.setPageNo(pageNo);
        result.setPageSize(pageSize);
        result.setTotalNum(commodityMapper.queryAllTotalNum());
        result.setList(getCommodityDTOList(new ArrayList<>(), commodities));
        return result;
    }
    private List<CommodityDTO> getCommodityDTOList(List<CommodityDTO> result, List<Commodity> commodities) {
        for (Commodity commodity : commodities) {
            CommodityDTO dto = new CommodityDTO(commodity);
            dto.setBrand(brandMapper.queryBrandById(commodity.getBrandID()));
            dto.setCategory(categoryMapper.queryCategoryById(commodity.getCategoryID()));
            result.add(dto);
        }
        return result;
    }
}
