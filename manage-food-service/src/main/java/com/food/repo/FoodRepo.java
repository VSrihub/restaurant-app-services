/**
 * 
 */
package com.food.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.model.Food;

/**
 * @author nsanda
 *
 */
public interface FoodRepo extends JpaRepository<Food, Integer>{

}
