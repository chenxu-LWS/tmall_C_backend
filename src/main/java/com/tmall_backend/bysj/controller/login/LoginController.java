package com.tmall_backend.bysj.controller.login;

import static com.tmall_backend.bysj.common.constants.Constants.COOKIE_KEY;
import static com.tmall_backend.bysj.common.constants.Constants.SESSION_KEY;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyun.oss.OSSException;
import com.tmall_backend.bysj.common.ReturnObject;
import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.controller.login.dto.ChangeIconDTO;
import com.tmall_backend.bysj.controller.login.dto.CustomerDTO;
import com.tmall_backend.bysj.controller.login.dto.IsLoginDTO;
import com.tmall_backend.bysj.controller.login.dto.LoginDTO;
import com.tmall_backend.bysj.entity.Customer;
import com.tmall_backend.bysj.service.login.LoginService;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Controller
public class LoginController {

    Logger logger = Logger.getLogger(LoginController.class);

    private static final Integer COOKIE_TIME_OUT = 24 * 60 * 60;


    @Autowired
    LoginService loginService;

    @PostMapping("/api/register")
    @ResponseBody
    public ReturnObject register(@RequestBody CustomerDTO dto) {
        if (isNotValidDTO(dto)) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        if (dto.getSex() != 0 && dto.getSex() != 1) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            Integer result = loginService.register(
                    new Customer(null, dto.getName(), dto.getPassword(), dto.getAge(), dto.getSex(), dto.getIcon()));
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @PostMapping("/api/login")
    @ResponseBody
    public ReturnObject login(HttpServletRequest req, HttpServletResponse resp, @RequestBody LoginDTO dto) {
        if (dto.getName() == null || dto.getPassword() == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            // 登陆
            loginService.login(dto.getName(), dto.getPassword());
            // 设置cookie
            Cookie cookie = new Cookie(COOKIE_KEY,dto.getName());
            cookie.setMaxAge(COOKIE_TIME_OUT);
            cookie.setPath("/");
            resp.addCookie(cookie);
            // 设置session
            req.getSession().setAttribute(SESSION_KEY, dto.getName());
            return new ReturnObject(true, null, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    private boolean isNotValidDTO(CustomerDTO dto) {
        return dto == null || dto.getName() == null || dto.getPassword() == null
                || dto.getName().length() > 50 || dto.getPassword().length() > 50
                || dto.getSex() == null || dto.getAge() == null || dto.getIcon() == null;
    }

    @RequestMapping("/api/logout")
    @ResponseBody
    public ReturnObject logout(HttpServletRequest req, HttpServletResponse resp) {
        // 删除session
        req.getSession().invalidate();
        // 删除cookie
        Cookie[] cookies = req.getCookies();
        if (null==cookies) {
            logger.error("cookie删除失效");
        } else {
            for(Cookie cookie : cookies){
                //如果找到同名cookie，就将value设置为null，将存活时间设置为0，再替换掉原cookie，这样就相当于删除了。
                if(cookie.getName().equals(COOKIE_KEY)){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                    break;
                }
            }
        }
        return new ReturnObject(true, null, 0);
    }

    @RequestMapping("/api/isLogin")
    @ResponseBody
    public ReturnObject isLogin(HttpServletRequest req) {
        boolean isLogin = false;
        Cookie[] cs =  req.getCookies();
        if(cs != null && cs.length > 0) {
            for(Cookie c : cs) {
                if(COOKIE_KEY.equals(c.getName())) {
                    req.getSession().setAttribute(SESSION_KEY, c.getValue());
                    isLogin = true;
                }
            }
        }
        final IsLoginDTO isLoginDTO = new IsLoginDTO();
        if (req.getSession().getAttribute(SESSION_KEY) != null || isLogin) {
            isLoginDTO.setIsLogin(true);
            isLoginDTO.setUserName((String) req.getSession().getAttribute(SESSION_KEY));
        } else {
            isLoginDTO.setIsLogin(false);
        }
        return new ReturnObject(true, isLoginDTO, 0);
    }

    @RequestMapping("/api/getCurrentLoginCustomer")
    @ResponseBody
    public ReturnObject getCurrentLoginCustomer(HttpServletRequest req) {
        boolean isLogin = false;
        String custName = null;
        Cookie[] cs =  req.getCookies();
        if(cs != null && cs.length > 0) {
            for(Cookie c : cs) {
                if(COOKIE_KEY.equals(c.getName())) {
                    req.getSession().setAttribute(SESSION_KEY, c.getValue());
                    isLogin = true;
                    custName = c.getValue();
                }
            }
        }
        if (req.getSession().getAttribute(SESSION_KEY) != null || isLogin) {
            if (custName == null) {
                custName = (String) req.getSession().getAttribute(SESSION_KEY);
            }
        } else {
            return new ReturnObject(ErrInfo.GET_LOGIN_USER_ERROR);
        }
        try {
            final Customer customerInfo = loginService.getCustomerInfo(custName);
            return new ReturnObject(true, customerInfo, 0);
        } catch (OSSException oe) {
            logger.info("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            logger.info("Error Message:" + oe.getErrorMessage());
            logger.info("Error Code:" + oe.getErrorCode());
            logger.info("Request ID:" + oe.getRequestId());
            logger.info("Host ID:" + oe.getHostId());
            return new ReturnObject(ErrInfo.OSS_ERROR);
        } catch (Throwable ce) {
            logger.info("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            logger.info("Error Message:" + ce.getMessage());
            return new ReturnObject(ErrInfo.OSS_ERROR);
        }
    }

    @RequestMapping("/api/changeIcon")
    @ResponseBody
    public ReturnObject changeIcon(HttpServletRequest req, @RequestBody ChangeIconDTO dto) {
        if (dto.getNewIcon() == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final Integer result = loginService.changeIcon(req, dto.getNewIcon());
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }
}
