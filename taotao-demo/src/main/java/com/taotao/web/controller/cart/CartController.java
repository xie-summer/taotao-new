package com.taotao.web.controller.cart;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.whats.core.Result;
import com.taotao.whats.core.ResultGenerator;
import com.taotao.whats.entity.Cart;
import com.taotao.whats.service.cart.CartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sunder on 2017/08/15.
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    private CartService tCartService;

    @PostMapping
    public Result add(Cart cart) {
        tCartService.save(cart);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        //userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(Cart cart) {
        tCartService.update(cart);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Cart cart = tCartService.findById(id);
        return ResultGenerator.genSuccessResult(cart);
    }

    @GetMapping
    public Result list(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Cart> list = tCartService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
