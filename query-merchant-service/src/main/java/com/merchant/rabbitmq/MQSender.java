package com.merchant.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.merchant.order.CustomerOrder;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class MQSender {


	@Autowired
	AmqpTemplate rabbitTemplate;
	
	
	@Value("${order.rabbitmq.exchange}")
	String exchangeName;
	
	@Value("${order.rabbitmq.routingKey}")
	String routingKey;
	
	public void sendOrPlaceOrderRequest(CustomerOrder  orderEvent) {
		rabbitTemplate.convertAndSend(exchangeName, routingKey,orderEvent);
		log.info("Order Placed event published");
	}
}
