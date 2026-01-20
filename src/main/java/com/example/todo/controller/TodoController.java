package com.example.todo.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoController {

	@GetMapping("/todo")
	public String todo(Model model) {
		List<String> items = List.of(
				"Doc tai lieu Spring MVC",
				"Lam giao dien Todo",
				"Hoan thien them/sua/xoa"
		);
		model.addAttribute("items", items);
		return "todo";
	}
}
