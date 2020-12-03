/**
 * 
 */
package com.order.aggregate;

import java.util.List;
import java.util.Map;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.order.command.PlaceOrderCommand;
import com.order.event.PlaceOrderEvent;

import lombok.Getter;

/**
 * @author nsanda
 *
 */
@Aggregate
@Getter
public class OrderAggregator {

	@AggregateIdentifier
	private String id;
	
	private String restaurantId;
	private String customerId;
	private List<Map<Object, Object>> dishes;
	
	private String status;
	
	/*@CommandHandler
	public void OrderAggregate(PlaceOrderCommand orderCommand) {
		System.out.println("orderCommand id "+orderCommand.getId());
		AggregateLifecycle.apply(new PlaceOrderEvent(orderCommand.getId(), 
				orderCommand.getRestaurantId(), orderCommand.getDishes(), "IN PROGRESS"));
	}*/
	
	public OrderAggregator() {
		
	}
	
	@CommandHandler
	public OrderAggregator(PlaceOrderCommand orderCommand) {
		System.out.println("orderCommand id "+orderCommand.getId());
		
		AggregateLifecycle.apply(new PlaceOrderEvent(orderCommand.getId(), 
				orderCommand.getRestaurantId(), orderCommand.getDishes(), "IN PROGRESS"));
		
		//mqSender.sendOrPlaceOrderRequest(event);
		
	}
	
	@EventHandler
	public void on(PlaceOrderEvent orderEvent) {
		this.id = orderEvent.getId();
		this.restaurantId = orderEvent.getRestaurantId();
		//this.customerId = orderEvent.getc
		this.dishes = orderEvent.getDishes();
		this.status = orderEvent.getStatus();
	}
	
}
