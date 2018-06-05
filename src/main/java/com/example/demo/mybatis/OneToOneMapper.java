package com.example.demo.mybatis;

import com.example.demo.mybatis.model.Card;
import com.example.demo.mybatis.model.Person;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

public interface OneToOneMapper {
    @Select("select * from tb_card where id = #{id}")
    Card selectCardById(Integer id);

    @Select("select * from tb_person where id = #{id}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="name",property="name"),
            /*@Result(column="sex",property="sex"),
            @Result(column="age",property="age"),*/
            /*@Result如果数据库字段与实体字段名称不一致，就用它来映射，不然这个字段可省略，结果一样*/
            @Result(column="card_id",property="card",//这句不能省略，不然去查card实体
                    one=@One(
                            select="com.example.demo.mybatis.OneToOneMapper.selectCardById",
                            fetchType= FetchType.EAGER
                    ))
    })
    Person selectPersonById(Integer id);
    /*日志输出的语句如下：
    ==>  Preparing: select * from tb_person where id = ?
            ==> Parameters: 2(Integer)
            <==    Columns: id, name, sex, age, card_id
<==        Row: 2, lisi, 1, 22, 2
            ====>  Preparing: select * from tb_card where id = ?
            ====> Parameters: 2(Integer)
            <====    Columns: id, code
<====        Row: 2, c002
<====      Total: 1
            <==      Total: 1*/
}
