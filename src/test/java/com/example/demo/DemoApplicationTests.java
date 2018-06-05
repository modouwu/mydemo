package com.example.demo;

import com.example.demo.service.ResuleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Resource
	ResuleService resuleService;

	@Test
	public void OneToOneTest() {
		resuleService.selectCardById(2);
		resuleService.selectPersonById(2);
	}

	@Test
	public void OneToManyTest() {
		resuleService.selectByClazzId(2);
		resuleService.selectClazzByIdToManyStudent(2);
	}

	@Test
	public void ManyToManyTest() {
		resuleService.selectUserById(3001);
		resuleService.selectArticles(2);
		resuleService.selectOrderById(2);
		resuleService.selectOrders(2);
		resuleService.selectArticleById(12);
	}

}
