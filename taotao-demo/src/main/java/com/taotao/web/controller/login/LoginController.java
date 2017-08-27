package com.taotao.web.controller.login;

import com.taotao.core.Result;
import com.taotao.core.ResultCode;
import com.taotao.entity.User;
import com.taotao.service.user.UserService;
import com.taotao.web.constant.PageEnum;
import com.taotao.web.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sunder on 2017/7/6.
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String loginByPwd(User user) {
        Result result = userService.loginByPwd(user);
        if (null != null && result.getCode() == ResultCode.SUCCESS.code) {
            return PageEnum.INDEX.getValue();
        }
        return PageEnum.LOGIN.getValue();
    }

}
