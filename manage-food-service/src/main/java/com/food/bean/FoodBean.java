/**
 * 
 */
package com.food.bean;

import java.io.Serializable;

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

	private int id;
	
	private String catName;
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
