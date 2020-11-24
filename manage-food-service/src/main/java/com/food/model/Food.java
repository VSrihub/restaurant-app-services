/**
 * 
 */
package com.food.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * @author nsanda
 *
 */
@Entity
@Setter
@Getter
public class Food implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7032193364160823070L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String catName;
	private String foodName;
	private String image;
	private String notes;
	private String description;
	private String vat;
	private String cookingTime;
	
	
	private String  offer;
	private String special;
	private String status;
	

}
