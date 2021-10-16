package com.simplilearn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc")
public class ThymeleafController {

	@GetMapping("/view")
	public String whatIsCool() {
		return "thymeleaf-is-cool";
	}
	@GetMapping("/model")
	public String whatCanPassToView(Model model) {
		model.addAttribute("attributeKey", "This is attribute key");
		return "thymeleaf-is-cool";
	}
}
