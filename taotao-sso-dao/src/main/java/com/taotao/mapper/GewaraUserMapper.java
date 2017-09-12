package com.taotao.mapper;

import com.taotao.model.acl.GewaraUser;
import com.taotao.model.acl.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface GewaraUserMapper extends Mapper<User> {
    @Select(" select * from user u where u.username =#{name}")
    GewaraUser findByUserName(@Param("name")String name);
}
