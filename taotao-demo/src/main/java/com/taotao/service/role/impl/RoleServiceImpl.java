package com.taotao.service.role.impl;

import com.taotao.core.AbstractService;
import com.taotao.entity.Role;
import com.taotao.mapper.role.RoleMapper;
import com.taotao.service.role.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by sunder on 2017/08/15.
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {
    @Resource
    private RoleMapper roleMapper;

}
