package com.example.demo.mybatis.model;

import java.io.Serializable;
import java.util.List;

public class Clazz implements Serializable {
    private Integer id;//班级id
    private String code;//班级编号
    private String name;//班级名称
    private List<Student> students;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
