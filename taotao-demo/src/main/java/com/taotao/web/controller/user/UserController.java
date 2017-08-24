package com.taotao.web.controller.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.whats.core.Result;
import com.taotao.whats.core.ResultGenerator;
import com.taotao.whats.entity.User;
import com.taotao.whats.service.user.UserService;
import com.taotao.whats.web.controller.base.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/06/30.
*/
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;


    @PostMapping
    public Result add(User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @GetMapping
    public Result list(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
