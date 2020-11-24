/**
 * 
 */
package com.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.bean.OrderBean;
import com.order.command.PlaceOrderCommand;
import com.order.mapper.DataMapper;
import com.order.model.Order;
import com.order.repo.OrderRepo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nsanda
 *
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private CommandGateway  commandGateway;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Order.service.OrderService#save(com.Order.bean.OrderBean)
	 */
	@Override
	public OrderBean placeOrder(OrderBean orderBean) {
		Order orderModel = DataMapper.mapBeanToModel(orderBean);
		
		String orderId = UUID.randomUUID().toString();
		log.info("Generated Order Id is:\t"+orderId);
		
		PlaceOrderCommand orderCommand = new PlaceOrderCommand(orderId, 
				orderBean.getRestaurantId(), orderBean.getDishes());
		
		commandGateway.send(orderCommand);
		
		
		
		
		
		
		
		orderRepo.save(orderModel);
		return DataMapper.mapModelToBean(orderModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Order.service.OrderService#findorderById(int)
	 */
	@Override
	public OrderBean findOrderById(int id) {
		Order orderModel = orderRepo.getOne(id);
		return DataMapper.mapModelToBean(orderModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Order.service.OrderService#findorderByName(java.lang.String)
	 */
	@Override
	public OrderBean findOrderByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Order.service.OrderService#findAll()
	 */
	@Override
	public List<OrderBean> findAll() {
		List<OrderBean> orderBeanList = new ArrayList<>();
		List<Order> orderModelList = orderRepo.findAll();
		orderModelList.stream().forEach(orderModel -> {
			orderBeanList.add(DataMapper.mapModelToBean(orderModel));
		});
		return orderBeanList;
	}

	@Override
	public List<OrderBean> deleteOrder(int id) {
		Order dbOrder = orderRepo.getOne(id);
		if (dbOrder != null) {
			orderRepo.delete(dbOrder);
		}

		return findAll();
	}

}
