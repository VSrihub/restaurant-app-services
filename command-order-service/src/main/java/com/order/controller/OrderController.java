/**
 * 
 */
package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.bean.CustomerOrderResp;
import com.order.bean.OrderBean;
import com.order.event.PlaceOrderEvent;
import com.order.rabbitmq.MQSender;
import com.order.service.OrderService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nsanda
 *
 */

@RestController
@CrossOrigin(allowedHeaders = "*")
@RequestMapping("/api")
@Slf4j
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MQSender mqSender;

	@PostMapping("/order")
	public ResponseEntity<? extends Object> addOrder(@RequestBody OrderBean orderBean) {
		CustomerOrderResp orderResp = null;
		try {
			orderResp = orderService.placeOrder(orderBean);
		} catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(orderResp, HttpStatus.OK);
	}
	
	@GetMapping("/mqHealth")
	public void healthCheck() {
		PlaceOrderEvent event = new PlaceOrderEvent("dummyId", 
				"dummyId", null, "IN PROGRESS");
		mqSender.sendOrPlaceOrderRequest(event);
		
		
	}
	
	
}
