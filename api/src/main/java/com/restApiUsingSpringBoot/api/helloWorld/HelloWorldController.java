package com.restApiUsingSpringBoot.api.helloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping("/helloWorld")
	public String helloWorldPage() {
		return "hello World";
		
	}
	
	@GetMapping("/helloWorldMessage")
	public HelloWorldMessage welcomePage() {
		return new HelloWorldMessage("hey welcome!");
	}
	
	@GetMapping("/helloWorldMessage/path-variable/{name}")
	public HelloWorldMessage welcomePagePost(@PathVariable String name) {
		return new HelloWorldMessage("Hey " + name + " ,welcome !");
	}

	
}
