package com.taotao.web.controller.location;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.core.Result;
import com.taotao.core.ResultGenerator;
import com.taotao.entity.Location;
import com.taotao.service.location.LocationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sunder on 2017/07/07.
 */
@RestController
@RequestMapping("/location")
public class LocationController {
    @Resource
    private LocationService locationService;

    @PostMapping
    public Result add(Location location) {
        locationService.save(location);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        locationService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(Location location) {
        locationService.update(location);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Location location = locationService.findById(id);
        return ResultGenerator.genSuccessResult(location);
    }

    @GetMapping
    public Result list(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Location> list = locationService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
