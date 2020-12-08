/**
 * 
 */
package com.order.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author nsanda
 *
 */
@Entity
@Getter
@Setter
@Table(name="customer_order")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*@Id
	@GeneratedValue(strategy=GenerationType.AUTO)*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(unique=true)
	private String orderRunNumber;
	/*@ElementCollection(targetClass=Order.class)
	private List<String> dishes;*/
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<OrderItem> dishes;
	
	private String customerId;
	private String restaurantId;
	private String notes;
	private String status;
	

}
