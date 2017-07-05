package com.springboot.test.builderDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.test.builderDemo.controller.HelloController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuilderDemoApplication.class,
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BuilderDemoApplicationTests {

	@Autowired
	private HelloController controller;
	
	@Test
	public void contextLoads() {
		//String body = this.controller.getForObject("/hello", String.class);
        //assertThat(body).isEqualTo("ok");
	}

}
