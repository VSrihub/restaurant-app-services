package com.order.axon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.order.aggregate.OrderAggregator;
import com.order.event.PlaceOrderEvent;
import com.order.model.Order;
import com.order.model.OrderItem;
import com.order.repo.OrderRepo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderEntityManager {

	@Autowired
	private OrderRepo  orderRepo;
	
	@Autowired
	EventSourcingRepository<OrderAggregator>  orderEventSourcingRepo;
	
	@EventSourcingHandler
	public void on(PlaceOrderEvent event) {
		log.info("event ID taken by an event inn OrderEntityManager is:\t"+event.getId());
		OrderAggregator orderAggregator = orderEventSourcingRepo.
				load(event.getId()).getWrappedAggregate().getAggregateRoot();
		
		Order orderModel = new Order();
		orderModel.setCustomerId(orderAggregator.getCustomerId());
		//orderModel.setId(orderAggregator.getId());
		
		
		orderModel.setRestaurantId(orderAggregator.getRestaurantId());
		
		List<Map<Object, Object>> mapList = orderAggregator.getDishes();
		List<OrderItem> itemsList = new ArrayList<OrderItem>();
		if(mapList != null && mapList.size() >0) {
			
			mapList.stream().forEach(map->{
				if(map != null && map.size() >0) {
					
					map.keySet().stream().forEach(key->{
						OrderItem orderItem = new OrderItem();
						String dishName = (String) map.get(key);
						orderItem.setCategory(key.toString());
						orderItem.setItemName(dishName);
						itemsList.add(orderItem);
					});
				}
			});
		}
		
		orderModel.setDishes(itemsList);
		orderModel.setStatus(orderAggregator.getStatus());
		orderModel.setOrderRunNumber(orderAggregator.getId());
		
		orderRepo.save(orderModel);
		
		
	}
	
}
