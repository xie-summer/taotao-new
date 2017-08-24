package com.taotao.service.cart.impl;

import com.taotao.whats.core.AbstractService;
import com.taotao.whats.entity.Cart;
import com.taotao.whats.mapper.cart.CartMapper;
import com.taotao.whats.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by sunder on 2017/08/15.
 */
@Service
@Transactional
public class TCartServiceImpl extends AbstractService<Cart> implements CartService {
    @Autowired
    private CartMapper tCartMapper;

}
