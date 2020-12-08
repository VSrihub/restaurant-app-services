package com.merchant.rabbitmq;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.merchant.order.CustomerOrder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MQConsumer {

	@Autowired
	MQSender sender;
	/*@Autowired
	private CommandGateway commandGateway;*/
	
	@RabbitListener(queues="${order.rabbitmq.queue}",containerFactory="listenerFactory")
	public void receiveOrderRequest(CustomerOrder  orderEvent) {
		log.info("event receieved from the queue is:\t"+orderEvent.getId());
		for(Map itemsMap: orderEvent.getDishes()) {
			itemsMap.keySet().stream().forEach(key->{
				log.info("dish is:\t"+itemsMap.get(key));
			});
		}
		
		orderEvent.setStatus("FOOD IS DELIVERED");
		
		sender.sendOrPlaceOrderRequest(orderEvent);
		
	}
	
	
}
