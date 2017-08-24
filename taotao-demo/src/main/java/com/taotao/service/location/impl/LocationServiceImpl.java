package com.taotao.service.location.impl;

import com.taotao.whats.core.AbstractService;
import com.taotao.whats.entity.Location;
import com.taotao.whats.mapper.location.LocationMapper;
import com.taotao.whats.service.location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by sunder on 2017/07/07.
 */
@Service
@Transactional
public class LocationServiceImpl extends AbstractService<Location> implements LocationService {
    @Autowired
    private LocationMapper locationMapper;

}
