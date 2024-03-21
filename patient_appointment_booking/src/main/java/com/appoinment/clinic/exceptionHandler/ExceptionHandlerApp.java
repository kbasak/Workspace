package com.appoinment.clinic.exceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.appoinment.clinic.exceptions.UserAlreadyExistException;

@RestControllerAdvice
public class ExceptionHandlerApp {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> applicationExceptionHandler(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, String> handleFindUserException(UserAlreadyExistException ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("ErrorMessage", ex.getMessage());
		return errorMap;
	}
}
