/**
 * 
 */
package com.food.service;

import java.util.List;

import com.food.bean.FoodBean;

/**
 * @author nsanda
 *
 */
public interface FoodService {

	public FoodBean  save(FoodBean foodBean);
	public FoodBean  findCatById(int id);
	public FoodBean findCatByName(String name);
	public List<FoodBean>  findAll();
	public List<FoodBean> deleteFood(int id);
}
