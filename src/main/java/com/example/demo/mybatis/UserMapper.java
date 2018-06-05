package com.example.demo.mybatis;

import com.example.demo.mybatis.model.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from tb_person where name=#{username}")
        //@Result 修饰返回的结果集，关联实体类属性和数据库字段一一对应，如果实体类属性和数据库属性名保持一致，就不需要这个属性来修饰。
    User findUserByUsername(String username);
}
