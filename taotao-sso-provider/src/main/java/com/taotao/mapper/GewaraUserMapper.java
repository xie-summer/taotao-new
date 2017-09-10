package com.taotao.mapper;

import com.taotao.acl.GewaraUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface GewaraUserMapper extends Mapper<GewaraUser> {
    @Select(" select * from user u where u.username =#{name}")
    GewaraUser findByUserName(@Param("name")String name);
}
