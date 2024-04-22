package com.restApiUsingSpringBoot.api.helloWorld;

public class HelloWorldMessage {
	
	private String message;

	public HelloWorldMessage() {
		
	}
	public HelloWorldMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldMessage [message=" + message + "]";
	}
	
	
	

}
