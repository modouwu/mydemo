package com.example.demo.service;

import com.example.demo.mybatis.ManyToManyMapper;
import com.example.demo.mybatis.OneToManyMapper;
import com.example.demo.mybatis.OneToOneMapper;
import com.example.demo.mybatis.model.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@Transactional
public class ResuleService {
    @Resource
    OneToOneMapper oneToOneMapper;

    public void selectCardById(int id){
        Card card= oneToOneMapper.selectCardById(id);
        System.out.println(card.toString());
    }

    public void selectPersonById(int id){
        Person person= oneToOneMapper.selectPersonById(id);
        System.out.println(person.toString());
    }

    @Resource
    OneToManyMapper oneToManyMapper;

    public void selectByClazzId(int id){
        List<Student> list = oneToManyMapper.selectByClazzId(id);
        System.out.println(list.toString());
    }

    public void selectClazzByIdToManyStudent(int id){
        Clazz clazz= oneToManyMapper.selectClazzByIdToManyStudent(id);
        System.out.println(clazz.toString());
    }

    @Resource
    ManyToManyMapper manyToManyMapper;

    public void selectArticles(Integer id){
        List<Article> list = manyToManyMapper.selectArticles(id);
        System.out.println(list.toString());
    }

    public void selectOrderById(Integer id){
        Order order = manyToManyMapper.selectOrderById(id);
        System.out.println(order.toString());
    }

    public void selectOrders(Integer id){
        List<Order> list = manyToManyMapper.selectOrders(id);
        System.out.println(list.toString());
    }

    public void selectArticleById(Integer id){
        Article article = manyToManyMapper.selectArticleById(id);
        System.out.println(article.toString());
    }

    public void selectUserById(Integer id){
        OrderUser orderUser = manyToManyMapper.selectUserById(id);
        System.out.println(orderUser.toString());
    }



}
