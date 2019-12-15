package com.dreamworks.employee.exception;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoSocketOpenException;
import com.mongodb.MongoTimeoutException;

@ControllerAdvice
@RestController
public class CustomExceptionHandler {
	
	Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		logger.error(ex.getMessage());
		StringBuilder builder=new StringBuilder();
		builder.append("Following fields cannot be null: ");
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			builder.append(fieldName);
			builder.append(",");
		});
		builder.deleteCharAt(builder.length()-1);
		return buildErrorBody("VALIDATION_ERROR", builder.toString());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	public Map<String, String> handleCustomValidationExceptions(ValidationException ex) {
		logger.error(ex.getMessage());
		return buildErrorBody("VALIDATION_ERROR", ex.getMessage());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(MongoTimeoutException.class)
	public Map<String, String> handleDBConnectionTimeOutExceptions(MongoTimeoutException ex) {
		logger.error(ex.getMessage());
		return buildErrorBody("INTERNAL_ERROR", "Something went wrong, try after some time");
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ConnectException.class)
	public Map<String, String> handleDBSocketExceptions(ConnectException ex) {
		logger.error(ex.getMessage());
		return buildErrorBody("INTERNAL_ERROR", "Something went wrong, try after some time");
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(MongoSocketOpenException.class)
	public Map<String, String> handleDBSocketExceptions(MongoSocketOpenException ex) {
		logger.error(ex.getMessage());
		return buildErrorBody("INTERNAL_ERROR", "Something went wrong, try after some time");
	}
	
	public Map<String, String> buildErrorBody(String errorCode, String errorMessage) {
		Map<String, String> errors = new HashMap<>();
		errors.put("error_code", errorCode);
		errors.put("error_message", errorMessage);
		return errors;
	}
}
