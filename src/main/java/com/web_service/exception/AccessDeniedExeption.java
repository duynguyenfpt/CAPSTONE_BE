package com.web_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.web_service.api.output.ObjectOuput;
import com.web_service.dto.ServerInfoDTO;

@RestControllerAdvice
public class AccessDeniedExeption {
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ObjectOuput<String>> processRuntimeException(RuntimeException e) {
		ObjectOuput<String> result = new ObjectOuput<String>();
		result.setData("a");
		result.setCode("200");

		return new ResponseEntity<ObjectOuput<String>>(result, HttpStatus.FORBIDDEN);

	}

}
