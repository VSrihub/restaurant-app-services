/**
 * 
 */
package com.category.service;

import java.util.List;

import com.category.bean.CategoryBean;

/**
 * @author nsanda
 *
 */
public interface CategoryService {

	public CategoryBean  save(CategoryBean catBean);
	public CategoryBean  findCatById(int id);
	public CategoryBean findCatByName(String name);
	public List<CategoryBean>  findAll();
	public List<CategoryBean> deleteCategory(int id);
}
