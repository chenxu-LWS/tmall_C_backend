package com.tmall_backend.bysj.service.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall_backend.bysj.entity.Brand;
import com.tmall_backend.bysj.mapper.BrandMapper;


/**
 * @author LiuWenshuo
 * Created on 2022-03-07
 */
@Service
public class BrandService {
    @Autowired
    BrandMapper brandMapper;

    public Brand queryBrandById(Integer id) {
        return brandMapper.queryBrandById(id);
    }

}
