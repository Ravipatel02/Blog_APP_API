package com.masai.blog.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.masai.blog.payloads.ApiResponce;

@RestControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> resourceNotFoundException(ResourceNotFoundException ex){
		String message = ex.getMessage();
		ApiResponce responce = new ApiResponce(message , false);
		return new ResponseEntity<ApiResponce>(responce,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArrgmentNotValidException(MethodArgumentNotValidException ex){
		Map<String , String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error ->{
			 String field = ((FieldError)error).getField();
			 String message = error.getDefaultMessage();
			 resp.put(field, message);
			 
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}

}
