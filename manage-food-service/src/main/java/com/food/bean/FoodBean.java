/**
 * 
 */
package com.food.bean;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author nsanda
 *
 */
@Setter
@Getter
public class FoodBean implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7032193364160823070L;
	
	public FoodBean() {
	}

	private int id;
	
	/*@NotEmpty(message="Category should be present to created Food")
	@NotNull(message="Category should not be blank or null")*/
	private String catName;
	
	/*@NotEmpty(message="foodName should be present to created Food")
	@NotNull(message="foodName should not be blank")*/
	private String foodName;
	private String image;
	private String notes;
	private String description;
	private String vat;
	private String cookingTime;
	private boolean offer;
	private boolean special;
	private boolean status;
	

}
