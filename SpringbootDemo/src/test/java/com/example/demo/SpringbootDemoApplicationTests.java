package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootDemoApplication.class)
@DirtiesContext
public class SpringbootDemoApplicationTests {
	@Test
	public void contextLoads() throws Exception{
	    System.out.println("Success Startup");
	}

}
