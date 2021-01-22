/**
 * 
 */
package com.food.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * @author nsanda
 *
 */
@Getter
public class FoodApiError {

	private String message;
	private String statusCode;
	private LocalDateTime timeStamp;
	
	public FoodApiError() {
		this.timeStamp=LocalDateTime.now();
	}

	public FoodApiError(HttpStatus status, Throwable tx) {
		this.message = tx.getMessage();
		this.statusCode = status.name();
		this.timeStamp = LocalDateTime.now();
	}
	
	public FoodApiError(String message, String statusCode, LocalDateTime timeStamp) {
		this.message = message;
		this.statusCode = statusCode;
		this.timeStamp = LocalDateTime.now();
	}
	
	
}
