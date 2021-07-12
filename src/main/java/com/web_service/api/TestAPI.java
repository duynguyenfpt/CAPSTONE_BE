package com.web_service.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TestAPI {
	@GetMapping(value = "/api/test")
	public String showAccount() {
			return "hello";
	}
}
