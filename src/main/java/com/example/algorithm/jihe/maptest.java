package com.example.algorithm.jihe;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class maptest {
    public static void main(String[] args) {


        Map<String, String> map = new LinkedHashMap<String, String>();
        //使用put添加元素
        map.put("name", "张三");
        map.put("sex", "男");
        map.put("age", "12");
        map.put("addres", "深圳");
        map.put("iPhone", "13068706819");
        //存放两个key-value为空的元素，打印时出现一个
        map.put(null, null);
        map.put(null, null);

        //打印出来元素是先进后出排序
        System.out.println("HashMap存放元素规则是先进后出：" + map);
    }

}
