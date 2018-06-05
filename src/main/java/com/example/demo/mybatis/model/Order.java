package com.example.demo.mybatis.model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private Integer id;//订单id
    private String code;//订单编号
    private Double total;//订单总金额
    private OrderUser user;
    private List<Article> articles;//一个订单可包含多种商品
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public OrderUser getUser() {
        return user;
    }
    public void setUser(OrderUser user) {
        this.user = user;
    }
    public List<Article> getArticles() {
        return articles;
    }
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
