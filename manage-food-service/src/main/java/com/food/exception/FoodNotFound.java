/**
 * 
 */
package com.food.exception;

import lombok.Getter;

/**
 * @author nsanda
 *
 */
@Getter
public class FoodNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public FoodNotFound() {
		// TODO Auto-generated constructor stub
	}

	public FoodNotFound(String message) {
		this.message = message;
	}
	
	public FoodNotFound(Throwable tx) {
		this.message = tx.getMessage();
	}
	
	
	

}
