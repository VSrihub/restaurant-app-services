/**
 * 
 */
package com.food.exception;

/**
 * @author nsanda
 *
 */
public class CreateFoodException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public CreateFoodException() {
		// TODO Auto-generated constructor stub
	}

	public CreateFoodException(String message) {
		this.message = message;
	}
	
	public CreateFoodException(Throwable tx) {
		this.message = tx.getMessage();
	}
	
	
	

}
