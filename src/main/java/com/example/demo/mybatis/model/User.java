package com.example.demo.mybatis.model;


import java.io.Serializable;
//必须实现了Serializable才能缓存
public class User implements Serializable {
    private String id;     // 表主键
    private String name; // 登录用户名
    private String sex; // 登录密码
    private String age; // 最高权限管理员; "1":root "0": notRoot
    /*@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerDate;*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getCard_id() {
        return card_id;
    }

    public void setCard_id(Integer card_id) {
        this.card_id = card_id;
    }

    private Integer card_id; // 账号状态： 0：正常；1：禁用
}
