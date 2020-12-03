/**
 * 
 */
package com.order.event;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;

/**
 * @author nsanda
 *
 */
@Getter
public class PlaceOrderEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String restaurantId;
	private List<Map<Object, Object>> dishes;
	
	private String status;

	public PlaceOrderEvent() {
		// TODO Auto-generated constructor stub
	}
	public PlaceOrderEvent(String id, String restaurantId, List<Map<Object, Object>> dishes, String status) {
		super();
		this.id = id;
		this.restaurantId = restaurantId;
		this.dishes = dishes;
		this.status = status;
	}
	

}
