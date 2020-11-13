package com.category.mapper;

import org.springframework.beans.BeanUtils;

import com.category.bean.CategoryBean;
import com.category.model.Category;


/**
 * map bean to model and vice versa
 * @author nsanda
 *
 */
public class DataMapper {

	public static Category mapBeanToModel(CategoryBean catBean) {
		Category catModel = new Category();
		BeanUtils.copyProperties(catBean, catModel);
		catModel.setIsOffer(String.valueOf(catBean.isOffer()));
		return catModel;
	}
	
	public static CategoryBean mapModelToBean(Category catModel) {
		CategoryBean catBean = new CategoryBean();
		BeanUtils.copyProperties(catModel, catBean);
		catBean.setOffer(Boolean.valueOf(catModel.getIsOffer()));
		return catBean;
	}
}
