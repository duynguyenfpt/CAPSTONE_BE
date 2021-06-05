package com.hoc.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoc.dto.NewDTO;
import com.hoc.dto.UserDTO;
import com.hoc.service.impl.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserAPI {
	
//	@PostMapping(value = "/new")
//	public NewDTO createNew(@RequestBody UserDTO model) {
//		return UserService.save(model);
//	}

}
