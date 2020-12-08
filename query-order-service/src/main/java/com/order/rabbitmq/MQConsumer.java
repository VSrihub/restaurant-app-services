package com.order.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.event.PlaceOrderEvent;
import com.order.model.Order;
import com.order.repo.OrderRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MQConsumer {

	@Autowired
	OrderRepo orderRepo;
	/*@Autowired
	private CommandGateway commandGateway;*/
	
	@RabbitListener(queues="${order.rabbitmq.queue}",containerFactory="listenerFactory")
	public void receiveOrderRequest(PlaceOrderEvent  orderEvent) {
		log.info("event receieved from the queue is:\t"+orderEvent.getStatus());
		Order dbOrder = orderRepo.findOrderByRunNumber(orderEvent.getId());
		dbOrder.setStatus(orderEvent.getStatus());
		
		orderRepo.save(dbOrder);
		
		
	}
	
	
}
