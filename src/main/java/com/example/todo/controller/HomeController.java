package com.example.todo.controller;

import com.example.todo.service.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	private final GreetingService greetingService;

	public HomeController(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	@GetMapping("/")
	public String home() {
		return greetingService.getWelcomeMessage();
	}
}
