package com.simplilearn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mvc")
public class SimpleController {
	
	@RequestMapping(value="/ping")
	@ResponseBody
	public String pingIng() {
		return "Pong";
	}
}