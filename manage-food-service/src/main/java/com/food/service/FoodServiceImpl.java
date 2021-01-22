/**
 * 
 */
package com.food.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.bean.FoodBean;
import com.food.exception.FoodNotFound;
import com.food.mapper.DataMapper;
import com.food.model.Food;
import com.food.repo.FoodRepo;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author nsanda
 *
 */
@Service
@Slf4j
@Setter
public class FoodServiceImpl implements FoodService {

	//@Autowired
	private FoodRepo foodRepo;
	/*public FoodServiceImpl(FoodRepo foodRepo) {
		this.foodRepo=foodRepo;
	}*/
	
	/* (non-Javadoc)
	 * @see com.category.service.CategoryService#save(com.category.bean.CategoryBean)
	 */
	@Override
	public FoodBean save(FoodBean foodBean) {
		Food FoodModel = DataMapper.mapBeanToModel(foodBean);
		
		
		
		foodRepo.save(FoodModel);
		
		return DataMapper.mapModelToBean(FoodModel);
	}

	/* (non-Javadoc)
	 * @see com.category.service.CategoryService#findCatById(int)
	 */
	@Override
	public FoodBean findCatById(int id) throws FoodNotFound {
		//Food FoodModel  = foodRepo.getOne(id);
		Optional<Food> foodOptional = foodRepo.findById(id);
		if(foodOptional.isPresent()) {
			return DataMapper.mapModelToBean(foodOptional.get());
		}else {
			throw new FoodNotFound("Food with the Id "+id+" not found");
		}
		
	}

	/* (non-Javadoc)
	 * @see com.category.service.CategoryService#findCatByName(java.lang.String)
	 */
	@Override
	public FoodBean findCatByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.category.service.CategoryService#findAll()
	 */
	@Override
	public List<FoodBean> findAll() {
		List<FoodBean> foodBeanList = new ArrayList<>();
		List<Food> FoodModelList = foodRepo.findAll();
		FoodModelList.stream().forEach(FoodModel->{
			foodBeanList.add(DataMapper.mapModelToBean(FoodModel));
		});
		return foodBeanList;
	}

	@Override
	public List<FoodBean> deleteFood(int id) {
		Food dbCategory = foodRepo.getOne(id);
		if(dbCategory != null) {
			foodRepo.delete(dbCategory);
		}
		
		return findAll();
	}

}
