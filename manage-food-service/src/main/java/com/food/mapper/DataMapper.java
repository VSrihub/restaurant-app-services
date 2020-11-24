package com.food.mapper;

import org.springframework.beans.BeanUtils;

import com.food.bean.FoodBean;
import com.food.model.Food;


/**
 * map bean to model and vice versa
 * @author nsanda
 *
 */
public class DataMapper {

	public static Food mapBeanToModel(FoodBean foodBean) {
		Food foodModel = new Food();
		BeanUtils.copyProperties(foodBean, foodModel);
		foodModel.setOffer(String.valueOf(foodBean.isOffer()));
		foodModel.setSpecial(String.valueOf(foodBean.isSpecial()));
		foodModel.setStatus(String.valueOf(foodBean.isStatus()));
		return foodModel;
	}
	
	public static FoodBean mapModelToBean(Food foodModel) {
		FoodBean foodBean = new FoodBean();
		BeanUtils.copyProperties(foodModel, foodBean);
		foodBean.setOffer(Boolean.valueOf(foodModel.getOffer()));
		foodBean.setSpecial(Boolean.valueOf(foodModel.getSpecial()));
		foodBean.setStatus(Boolean.valueOf(foodModel.getStatus()));
		return foodBean;
	}
}
