package com.example.todo.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

	public String getWelcomeMessage() {
		return "chào mừng đến với Spring MVC";
	}
}
