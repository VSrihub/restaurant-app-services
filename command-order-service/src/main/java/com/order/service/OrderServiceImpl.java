/**
 * 
 */
package com.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.bean.CustomerOrderResp;
import com.order.bean.OrderBean;
import com.order.command.PlaceOrderCommand;
import com.order.event.PlaceOrderEvent;
import com.order.mapper.DataMapper;
import com.order.model.Order;
import com.order.rabbitmq.MQSender;
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

	@Autowired
	private MQSender mqSender;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Order.service.OrderService#save(com.Order.bean.OrderBean)
	 */
	@Override
	public CustomerOrderResp placeOrder(OrderBean orderBean) {
		Order orderModel = DataMapper.mapBeanToModel(orderBean);
		
		String orderId = UUID.randomUUID().toString();
		log.info("Generated Order Id is:\t"+orderId);
		
		PlaceOrderCommand orderCommand = new PlaceOrderCommand(orderId, 
				orderBean.getRestaurantId(), orderBean.getDishes());
		
		PlaceOrderEvent event = new PlaceOrderEvent(orderCommand.getId(), 
				orderCommand.getRestaurantId(), orderCommand.getDishes(), "IN PROGRESS");
		
		mqSender.sendOrPlaceOrderRequest(event);
		//CompletableFuture<String> eventStatus = commandGateway.sendAndWait(orderCommand);
		CompletableFuture<String> eventStatus = commandGateway.send(orderCommand);
		
		log.info("is Ordet Placed?:\t"+eventStatus.isDone());
		log.info("is order placed exceptionally:\t"+eventStatus.isCompletedExceptionally());
		
		if(!eventStatus.isCompletedExceptionally()) {
			try {
				log.info("event status resp is:\t"+eventStatus.get());
				//Order order = orderRepo.findOrderByRunNumber(eventStatus.get());
				CustomerOrderResp resp = new CustomerOrderResp();
				resp.setOrderId(eventStatus.get());
				resp.setStatus("IN PROGRESS");
				return resp;
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			log.info("exceptionally order placed");
		}
		
		
		return null;
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

	@Override
	public CustomerOrderResp getOrderStatus(String orderRunNumber) {
		Order order = orderRepo.findOrderByRunNumber(orderRunNumber);
		CustomerOrderResp resp = new CustomerOrderResp();
		resp.setOrderId(order.getOrderRunNumber());
		resp.setStatus(order.getStatus());
		return resp;
	}

}
