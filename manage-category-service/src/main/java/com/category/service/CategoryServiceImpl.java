/**
 * 
 */
package com.category.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.category.bean.CategoryBean;
import com.category.mapper.DataMapper;
import com.category.model.Category;
import com.category.repo.CategoryRepo;

/**
 * @author nsanda
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo catRepo;
	
	/* (non-Javadoc)
	 * @see com.category.service.CategoryService#save(com.category.bean.CategoryBean)
	 */
	@Override
	public CategoryBean save(CategoryBean catBean) {
		Category catModel = DataMapper.mapBeanToModel(catBean);
		catRepo.save(catModel);
		
		return DataMapper.mapModelToBean(catModel);
	}

	/* (non-Javadoc)
	 * @see com.category.service.CategoryService#findCatById(int)
	 */
	@Override
	public CategoryBean findCatById(int id) {
		Category catModel  = catRepo.getOne(id);
		return DataMapper.mapModelToBean(catModel);
	}

	/* (non-Javadoc)
	 * @see com.category.service.CategoryService#findCatByName(java.lang.String)
	 */
	@Override
	public CategoryBean findCatByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.category.service.CategoryService#findAll()
	 */
	@Override
	public List<CategoryBean> findAll() {
		List<CategoryBean> catBeanList = new ArrayList<>();
		List<Category> catModelList = catRepo.findAll();
		catModelList.stream().forEach(catModel->{
			catBeanList.add(DataMapper.mapModelToBean(catModel));
		});
		return catBeanList;
	}

	@Override
	public List<CategoryBean> deleteCategory(int id) {
		Category dbCategory = catRepo.getOne(id);
		if(dbCategory != null) {
			catRepo.delete(dbCategory);
		}
		
		return findAll();
	}

}
