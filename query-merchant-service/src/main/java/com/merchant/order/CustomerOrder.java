package com.merchant.order;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CustomerOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
private  String id;
	
	private String restaurantId;
	private List<Map<Object, Object>> dishes;
	
	private String status;

	 public CustomerOrder() {
		// TODO Auto-generated constructor stub
	}
	public CustomerOrder(String id, String restaurantId, List<Map<Object, Object>> dishes, String status) {
		super();
		this.id = id;
		this.restaurantId = restaurantId;
		this.dishes = dishes;
		this.status = status;
	}
}
