package com.example.demo.mybatis;

import com.example.demo.mybatis.model.Article;
import com.example.demo.mybatis.model.Order;
import com.example.demo.mybatis.model.OrderUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface ManyToManyMapper {
    @Select("select * from tb_user where id = #{id}")
    OrderUser selectUserById(Integer id);

    @Select("select * from tb_article where id in (select article_id from tb_item where order_id = #{id})")
    List<Article> selectArticles(Integer id);

    @Select("select * from tb_order where id = #{id}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="code",property="code"),
            @Result(column="total",property="total"),
            @Result(column="user_id",property="user",
                    one=@One(
                            select="com.example.demo.mybatis.ManyToManyMapper.selectUserById",
                            fetchType= FetchType.EAGER
                    )
            ),
            @Result(column="id",property="articles",
                    many=@Many(
                            select="com.example.demo.mybatis.ManyToManyMapper.selectArticles",
                            fetchType=FetchType.LAZY
                    )
            )
    })
    Order selectOrderById(Integer id);

    @Select("select * from tb_order where id in(select order_id from tb_item where article_id = #{id})")
    List<Order> selectOrders(Integer id);

    @Select("select * from tb_article where id = #{id}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="name",property="name"),
            @Result(column="price",property="price"),
            @Result(column="remark",property="remark"),
            @Result(column="id",property="orders",
                    many=@Many(
                            select="com.example.demo.mybatis.ManyToManyMapper.selectOrders",
                            fetchType=FetchType.LAZY
                    )
            )
    })
    Article selectArticleById(Integer id);
}
