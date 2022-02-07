package com.tmall_backend.bysj.controller.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmall_backend.bysj.common.ReturnListObject;
import com.tmall_backend.bysj.common.ReturnObject;
import com.tmall_backend.bysj.controller.login.dto.CustomerDTO;
import com.tmall_backend.bysj.entity.Customer;
import com.tmall_backend.bysj.mapper.CustomerMapper;

/**
 * @author LiuWenshuo
 * Created on 2022-02-01
 */
@Controller
@RequestMapping("/api/*/customer")
public class CustomerController {
    @Autowired
    CustomerMapper customerMapper;

    @RequestMapping("/QueryCustomerById")
    @ResponseBody
    public ReturnObject queryCustomerById(String customerId) {
        ReturnObject returnObject = new ReturnObject();
        Customer customer = customerMapper.queryCustomerById(customerId);
        returnObject.setSuccess(true);
        returnObject.setResult(customer);
        return returnObject;
    }

    @RequestMapping("/QueryCustomerList")
    @ResponseBody
    public ReturnListObject queryCustomerList() {
        ReturnListObject returnObject = new ReturnListObject();
        List<Customer> customer = customerMapper.queryCustomerList();
        returnObject.setSuccess(true);
        returnObject.setResult(new ArrayList<>(customer));
        return returnObject;
    }

    @PostMapping(value = "/InsertCustomer")
    @ResponseBody
    public Object insertCustomer(@RequestBody CustomerDTO customerDTO) {
        customerMapper.insertCustomer(customerDTO.getName(), customerDTO.getPassword());
        return new ReturnObject(true, null, 0);
    }
}
