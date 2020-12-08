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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.bean.CustomerOrderResp;
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
	
	
	@GetMapping("/order/{orderRunNumber}/status")
	public ResponseEntity<? extends Object> addOrder(@PathVariable("orderRunNumber") String orderRunNumber) {
		CustomerOrderResp orderResp = null;
		try {
			orderResp = orderService.getOrderStatus(orderRunNumber);
		} catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(orderResp, HttpStatus.OK);
	}
	
	
}
