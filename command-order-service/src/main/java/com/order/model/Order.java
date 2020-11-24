/**
 * 
 */
package com.order.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
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
@Getter
@Setter
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*@Id
	@GeneratedValue(strategy=GenerationType.AUTO)*/
	@Id
	private int id;
	private String orderRunNumber;
	/*@ElementCollection(targetClass=Order.class)
	private List<String> dishes;*/
	private String customerId;
	private String restaurantId;
	private String notes;
	private String status;
	

}
