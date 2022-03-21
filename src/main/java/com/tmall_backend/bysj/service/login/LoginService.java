package com.tmall_backend.bysj.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.entity.Customer;
import com.tmall_backend.bysj.mapper.CustomerMapper;
import com.tmall_backend.bysj.mapper.TrolleyMapper;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Service
public class LoginService {
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    TrolleyMapper trolleyMapper;

    public Integer register(Customer customer) {
        // 判断是否有重名的
        if (customerMapper.queryCustomerByName(customer.getName()) != null) {
            throw new BizException(ErrInfo.REGISTER_ERR_NAME_EXISTS);
        }
        customerMapper.insertCustomer(customer);
        trolleyMapper.insert(customer.getId());
        return customer.getId();
    }

    public Integer login(String name, String password) {
        if (customerMapper.queryCustomerByNameAndPass(name, password) == null) {
            throw new BizException(ErrInfo.LOGIN_ERR_NAME_NOT_EXISTS);
        }
        return 0;
    }
}
