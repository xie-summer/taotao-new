package com.taotao.service.role.impl;

import com.taotao.whats.core.AbstractService;
import com.taotao.whats.entity.Role;
import com.taotao.whats.mapper.role.RoleMapper;
import com.taotao.whats.service.role.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by sunder on 2017/08/15.
 */
@Service
@Transactional
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {
    @Resource
    private RoleMapper roleMapper;

}
