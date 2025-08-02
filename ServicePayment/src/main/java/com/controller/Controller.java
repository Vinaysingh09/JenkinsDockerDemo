package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pay")
public class Controller {
	
	@GetMapping(value = "/service")
	public String getService() {
		return "Payment Service";
	}
}
