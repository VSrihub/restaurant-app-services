/**
 * 
 */
package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.bean.OrderBean;
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

	@PostMapping("/order")
	public ResponseEntity<? extends Object> addOrder(@RequestBody OrderBean orderBean) {
		try {
			orderBean = orderService.placeOrder(orderBean);
		} catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(orderBean, HttpStatus.OK);
	}
}
