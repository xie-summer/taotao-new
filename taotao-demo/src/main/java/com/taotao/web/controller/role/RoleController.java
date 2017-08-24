package com.taotao.web.controller.role;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.whats.core.Result;
import com.taotao.whats.core.ResultGenerator;
import com.taotao.whats.entity.Role;
import com.taotao.whats.service.role.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by sunder on 2017/08/15.
*/
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @PostMapping
    public Result add(Role tRole) {
        roleService.save(tRole);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
//        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(Role tRole) {
        roleService.update(tRole);
        return ResultGenerator.genSuccessResult();
    }
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Role tRole = roleService.findById(id);
        return ResultGenerator.genSuccessResult(tRole);
    }

    @GetMapping
    public Result list(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Role> list = roleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
