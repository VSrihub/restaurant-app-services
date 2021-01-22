package com.food;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.food.bean.FoodBean;
import com.food.exception.FoodNotFound;
import com.food.service.FoodService;

public class FoodServiceTest extends ManageFoodServiceApplicationTests{
	private static final int FOOD_ID = 1000;
	
	@Autowired
	private FoodService foodService;
	
	@BeforeClass
	public void setUp() {
		
	}
	
	@Test
	public void testFindCatById() throws FoodNotFound {
		FoodBean foodBean = foodService.findCatById(1);
		assertFalse(foodBean.isStatus());
		assertNotNull(foodBean);
		assertEquals(1, foodBean.getId());		
	}
	
	@Test
	public void testFindCatByName_Negative()  {
		FoodBean foodBean = foodService.findCatByName(null);
		//assertNotNull(foodBean);
		assertNull(foodBean);
		//assertEquals(1, foodBean.getId());		
	}
	
	
	@Test
	public void testFindCatById_Negative() {
		FoodBean foodBean;
		try {
			foodBean = foodService.findCatById(FOOD_ID);
		} catch (FoodNotFound e) {
			System.out.println(e.getMessage());;
			assertThatExceptionOfType(FoodNotFound.class);
			String errorMessage = "Food with the Id "+FOOD_ID+" not found";
			assertEquals(errorMessage, e.getMessage());
		}
		//assertNotNull(foodBean);
		
		//assertEquals(1, foodBean.getId());		
	}
}
