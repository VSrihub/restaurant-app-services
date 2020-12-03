/**
 * 
 */
package com.order.service;

import java.util.List;

import com.order.bean.CustomerOrderResp;
import com.order.bean.OrderBean;

/**
 * @author nsanda
 *
 */
public interface OrderService {

	public CustomerOrderResp  placeOrder(OrderBean OrderBean);
	public OrderBean  findOrderById(int id);
	public OrderBean findOrderByName(String name);
	public List<OrderBean>  findAll();
	public List<OrderBean> deleteOrder(int id);
	
	public CustomerOrderResp  getOrderStatus(String orderRunNumber);
	
	
}
