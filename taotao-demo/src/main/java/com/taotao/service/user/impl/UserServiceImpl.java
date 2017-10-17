package com.taotao.service.user.impl;

import com.taotao.core.AbstractService;
import com.taotao.core.Result;
import com.taotao.core.ResultGenerator;
import com.taotao.entity.User;
import com.taotao.mapper.user.UserMapper;
import com.taotao.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by CodeGenerator on 2017/06/30.
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result loginByPwd(User user) { //TODO demo 没有进行任务加密和校验
        User selectOne = userMapper.selectOne(user);
        if (Optional.ofNullable(selectOne).isPresent()) {
            return ResultGenerator.genSuccessResult(selectOne);
        }
        return ResultGenerator.genFailResult();
    }
}
