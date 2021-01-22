/**
 * 
 */
package com.food.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author nsanda
 *
 */
@ControllerAdvice
public class FoodApiExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(FoodNotFound.class)
	public ResponseEntity<FoodApiError>  handleFoodnotFoundException(FoodNotFound fne){
		FoodApiError foodApiError = new FoodApiError(HttpStatus.NOT_FOUND,fne);
		return new ResponseEntity<FoodApiError>(foodApiError,HttpStatus.OK);
	}
	
	@ExceptionHandler(CreateFoodException.class)
	public ResponseEntity<FoodApiError>  handleCreateFoodException(CreateFoodException fne){
		FoodApiError foodApiError = new FoodApiError(HttpStatus.NOT_FOUND,fne);
		return new ResponseEntity<FoodApiError>(foodApiError,HttpStatus.OK);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<FoodApiError>  handleRuntimeException(RuntimeException fne){
		FoodApiError foodApiError = new FoodApiError(HttpStatus.NOT_FOUND,fne);
		return new ResponseEntity<FoodApiError>(foodApiError,HttpStatus.OK);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		FoodApiErrorList errors = new FoodApiErrorList();
		List<FoodApiError> errorsList = new ArrayList<FoodApiError>();
		
		List<ObjectError> objectErrorList = ex.getBindingResult().getAllErrors();
		objectErrorList.stream().forEach(objectError->{
			String message = objectError.getDefaultMessage();
			
			FoodApiError apiError = new FoodApiError(message,
					HttpStatus.BAD_REQUEST.name(),
					LocalDateTime.now());
			errorsList.add(apiError);
		});
		errors.setErrorList(errorsList);
		
		return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST);
	}
}
