/**
 * 
 */
package com.category.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.category.model.Category;

/**
 * @author nsanda
 *
 */
public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
