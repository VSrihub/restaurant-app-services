package com.order.axon;

import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.order.aggregate.OrderAggregator;
import com.order.event.PlaceOrderEvent;
import com.order.model.Order;
import com.order.repo.OrderRepo;

@Component
public class OrderEntityManager {

	@Autowired
	private OrderRepo  orderRepo;
	
	@Autowired
	EventSourcingRepository<OrderAggregator>  orderEventSourcingRepo;
	
	public void on(PlaceOrderEvent event) {
		OrderAggregator orderAggregator = orderEventSourcingRepo.
				load(event.getId()).getWrappedAggregate().getAggregateRoot();
		
		Order orderModel = new Order();
		orderModel.setCustomerId(orderAggregator.getCustomerId());
		//orderModel.setId(orderAggregator.getId());
		orderModel.setRestaurantId(orderAggregator.getRestaurantId());
		//orderModel.setDishes(orderAggregator.getDishes());
		orderModel.setStatus(orderAggregator.getStatus());
		orderModel.setOrderRunNumber(orderAggregator.getId());
		
		orderRepo.save(orderModel);
		
		
	}
	
}
