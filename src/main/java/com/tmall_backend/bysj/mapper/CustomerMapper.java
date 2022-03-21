package com.tmall_backend.bysj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.tmall_backend.bysj.entity.Customer;

/**
 * @author LiuWenshuo
 * Created on 2022-01-30
 */
@Mapper
@Component
public interface CustomerMapper {
    public Customer queryCustomerById(String id);
    public List<Customer> queryCustomerList();
    public Integer insertCustomer(Customer customer);
    public Customer queryCustomerByName(@Param("name") String name);
    public Customer queryCustomerByNameAndPass(@Param("name") String name, @Param("password") String password);
}
