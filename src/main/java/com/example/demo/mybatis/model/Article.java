package com.example.demo.mybatis.model;

import java.io.Serializable;
import java.util.List;

public class Article implements Serializable {
    private Integer id;//商品id
    private String name;//商品名称
    private Double price;//商品价格
    private String remark;//商品描述
    private List<Order> orders;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
