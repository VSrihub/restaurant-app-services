/**
 * 
 */
package com.order.command;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Getter;

/**
 * @author nsanda
 *
 */
@Getter
public class PlaceOrderCommand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//@AggregateIdentifier
	@TargetAggregateIdentifier
	private final String id;
	
	private String restaurantId;
	private List<Map<Object, Object>> dishes;
	public PlaceOrderCommand(String id, String restaurantId, List<Map<Object, Object>> dishes) {
		super();
		this.id = id;
		this.restaurantId = restaurantId;
		this.dishes = dishes;
	}
	
	

}
