package com.tmall_backend.bysj.service.login;

import static com.tmall_backend.bysj.common.constants.Constants.SESSION_KEY;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.common.oss.OssClient;
import com.tmall_backend.bysj.entity.Customer;
import com.tmall_backend.bysj.mapper.CustomerMapper;
import com.tmall_backend.bysj.mapper.TrolleyMapper;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Service
public class LoginService {

    Logger logger = Logger.getLogger(LoginService.class);

    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    TrolleyMapper trolleyMapper;
    @Autowired
    OssClient ossClient;

    private static final String BUCKET_NAME = "tmall-customer-icon";

    public Integer register(Customer customer) {
        // 判断是否有重名的
        if (customerMapper.queryCustomerByName(customer.getName()) != null) {
            throw new BizException(ErrInfo.REGISTER_ERR_NAME_EXISTS);
        }
        // 图片上传到OSS
        String objName = "customer-" + customer.getName() + ".txt";
        ossClient.getOssClient().putObject(
                BUCKET_NAME, objName, new ByteArrayInputStream(customer.getIcon().getBytes()));
        // 入库
        customer.setIcon(objName);
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

    public Customer getCustomerInfo(String name) throws IOException {
        final Customer customer = customerMapper.queryCustomerByName(name);
        final OSSObject object =
                ossClient.getOssClient().getObject(BUCKET_NAME, customer.getIcon());
        BufferedReader reader = new BufferedReader(new InputStreamReader(object.getObjectContent()));
        StringBuilder builder = new StringBuilder();
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            builder.append(line);
        }
        reader.close();
        customer.setIcon(builder.toString());
        return customer;
    }

    public Integer changeIcon(HttpServletRequest req, String newIcon) throws BizException{
        String custName = (String) req.getSession().getAttribute(SESSION_KEY);
        if (custName == null) {
            throw new BizException(ErrInfo.GET_LOGIN_USER_ERROR);
        }
        Customer customer = customerMapper.queryCustomerByName(custName);
        String iconObjName = customer.getIcon();
        System.out.println(newIcon);
        try {
            ossClient.getOssClient().putObject(BUCKET_NAME, iconObjName,
                    new ByteArrayInputStream(newIcon.getBytes()));
            return 0;
        } catch (OSSException oe) {
            logger.info("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            logger.info("Error Message:" + oe.getErrorMessage());
            logger.info("Error Code:" + oe.getErrorCode());
            logger.info("Request ID:" + oe.getRequestId());
            logger.info("Host ID:" + oe.getHostId());
            throw new BizException(ErrInfo.OSS_ERROR);
        } catch (Throwable ce) {
            logger.info("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            logger.info("Error Message:" + ce.getMessage());
            throw new BizException(ErrInfo.OSS_ERROR);
        }
    }
}
