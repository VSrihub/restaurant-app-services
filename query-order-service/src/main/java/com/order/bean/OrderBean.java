/**
 * 
 */
package com.order.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author nsanda
 *
 */
@Getter
@Setter
public class OrderBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String orderId;
	private List<Map<Object, Object>> dishes;
	private String customerId;
	private String restaurantId;
	private String notes;
	

}
