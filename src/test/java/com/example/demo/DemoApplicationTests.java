package com.example.demo;

import com.example.demo.controller.HelloWorldController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletionStage;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads1() {
		HelloWorldController helloWorldController = new HelloWorldController();
		String response = helloWorldController.getHello2();
		assert(response.equals("hello, world2"));
	}

	@Test
	void contextLoads2() {
		HelloWorldController helloWorldController = new HelloWorldController();
		CompletionStage<String> response = helloWorldController.getHello();
		response.toCompletableFuture().join();
		assert(response.toCompletableFuture().join().equals("hello, world"));
	}

	
}
