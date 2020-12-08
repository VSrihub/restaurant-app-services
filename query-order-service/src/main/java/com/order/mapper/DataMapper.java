package com.order.mapper;

import org.springframework.beans.BeanUtils;

import com.order.bean.OrderBean;
import com.order.model.Order;


/**
 * map bean to model and vice versa
 * @author nsanda
 *
 */
public class DataMapper {

	public static Order mapBeanToModel(OrderBean orderBean) {
		Order orderModel = new Order();
		BeanUtils.copyProperties(orderBean, orderModel);
		return orderModel;
	}
	
	public static OrderBean mapModelToBean(Order orderModel) {
		OrderBean orderBean = new OrderBean();
		BeanUtils.copyProperties(orderModel, orderBean);
		return orderBean;
	}
}
