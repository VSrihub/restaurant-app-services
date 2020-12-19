/**
 * 
 */
package com.food.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.food.bean.FoodBean;
import com.food.mapper.DataMapper;
import com.food.model.Food;
import com.food.repo.CategoryServiceproxy;
import com.food.repo.FoodRepo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nsanda
 *
 */
@Service
@Slf4j
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepo foodRepo;
	
	@Autowired
	private CategoryServiceproxy categoryProxy;
	
	/* (non-Javadoc)
	 * @see com.category.service.CategoryService#save(com.category.bean.CategoryBean)
	 */
	@Override
	public FoodBean save(FoodBean foodBean) {
		try {
			ResponseEntity<? extends Object> data = categoryProxy.categoryById(foodBean.getCatName());
			log.info("data is:\t"+data.getBody());
			Map<String, String> mapData = new HashMap<>();
			if(data.getBody() != null) {
				mapData.putAll((Map<? extends String, ? extends String>) data.getBody());
				
				log.info("catName is:\t"+mapData.get("catName"));
					String catName = mapData.get("catName");
					log.info("catName is:\t"+catName);
					foodBean.setCatName(catName);
		}
		
		Food FoodModel = DataMapper.mapBeanToModel(foodBean);
		
		
		
		foodRepo.save(FoodModel);
		
		return DataMapper.mapModelToBean(FoodModel);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return foodBean;
		
		
	}

	/* (non-Javadoc)
	 * @see com.category.service.CategoryService#findCatById(int)
	 */
	@Override
	public FoodBean findCatById(int id) {
		Food FoodModel  = foodRepo.getOne(id);
		return DataMapper.mapModelToBean(FoodModel);
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
